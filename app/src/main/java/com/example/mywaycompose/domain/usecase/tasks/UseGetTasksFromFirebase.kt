package com.example.mywaycompose.domain.usecase.tasks

import android.util.Log
import com.example.mywaycompose.data.repository.firebase_models.FirebaseTask
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.model.toTask
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository
import com.example.mywaycompose.utils.Constants.TASKS_KIND
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetTasksFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    operator fun invoke(): Flow<Resource<List<Task>>> = callbackFlow {
        trySend(Resource.Loading())
        try {
            val tasks = remoteServiceRepository.getAppData<FirebaseTask>(TASKS_KIND)
            var open_flow: ProducerScope<Resource<List<Task>>>? = null
            tasks.onEach {tasks ->
                Log.d("efwsdfdfd","efsdfd")
                open_flow = this
                trySend(Resource.Success(tasks.map { it.toTask() }))
            }.launchIn(coroutineScope)

            awaitClose{open_flow!!.close()}

        }catch (e:Exception){
            Log.d("efwsdfdfd",e.toString())
            trySend(Resource.Error(e.toString()))
        }


    }

}