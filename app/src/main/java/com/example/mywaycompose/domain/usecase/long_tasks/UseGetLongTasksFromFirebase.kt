package com.example.mywaycompose.domain.usecase.long_tasks

import android.util.Log
import com.example.mywaycompose.data.repository.firebase_models.FirebaseLongTask
import com.example.mywaycompose.data.repository.firebase_models.toLongTask
import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.utils.Constants.LONG_TASKS_KIND
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetLongTasksFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    operator fun invoke():Flow<Resource<List<LongTask>>> = callbackFlow {

        trySend(Resource.Loading())

        try {
            var open_flow: ProducerScope<Resource<List<LongTask>>>? = null
            remoteServiceRepository.getAppData<FirebaseLongTask>(LONG_TASKS_KIND).onEach {tasks ->
                open_flow = this

                trySend(Resource.Success(tasks.map { it.toLongTask() }))
                this.close()
            }.launchIn(coroutineScope)
            awaitClose { open_flow!!.close() }

        }catch (e:Exception){

        }

    }

}