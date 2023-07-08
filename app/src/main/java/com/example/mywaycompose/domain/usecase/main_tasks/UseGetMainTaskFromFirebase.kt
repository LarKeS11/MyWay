package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.data.repository.firebase_models.FirebaseLongTask
import com.example.mywaycompose.data.repository.firebase_models.FirebaseMainTask
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.model.toMainTask
import com.example.mywaycompose.domain.model.toTask
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.utils.Constants.MAIN_TASKS_KIND
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetMainTaskFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    operator fun invoke(): Flow<Resource<List<Pair<Int, MainTask>>>> = callbackFlow {

        trySend(Resource.Loading())
        try {
            val tasks = remoteServiceRepository.getAppData<Pair<Int, FirebaseMainTask>>(MAIN_TASKS_KIND)
            var open_flow: ProducerScope<Resource<List<Pair<Int, MainTask>>>>? = null
            tasks.onEach {tasks ->
                open_flow = this
                trySend(Resource.Success(tasks.map { Pair(it.first, it.second.toMainTask())}))
            }.launchIn(coroutineScope)

            awaitClose{open_flow!!.close()}

        }catch (e:Exception){
            trySend(Resource.Error(e.toString()))
        }


    }

}