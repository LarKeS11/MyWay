package com.example.mywaycompose.domain.usecase.subtasks

import com.example.mywaycompose.data.repository.firebase_models.FirebaseSubtask
import com.example.mywaycompose.data.repository.firebase_models.toSubtask
import com.example.mywaycompose.data.repository.subtasks.DataRemoteSubtasksRepository
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.model.toTask
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.subtasks.RemoteSubtasksRepository
import com.example.mywaycompose.utils.Constants.SUBTASK_TASKS_KIND
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetSubtasksFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    operator fun invoke(): Flow<Resource<List<SubTask>>> = callbackFlow {

        trySend(Resource.Loading())
        try {
            val tasks = remoteServiceRepository.getAppData<FirebaseSubtask>(SUBTASK_TASKS_KIND)
            var open_flow: ProducerScope<Resource<List<SubTask>>>? = null
            tasks.onEach {
                open_flow = this
                trySend(Resource.Success(it.map { it.toSubtask() }))
            }.launchIn(coroutineScope)

            awaitClose{open_flow!!.close()}

        }catch (e:Exception){
            trySend(Resource.Error(e.toString()))
        }


    }

}