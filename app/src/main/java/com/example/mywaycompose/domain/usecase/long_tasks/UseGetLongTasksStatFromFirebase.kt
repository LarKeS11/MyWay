package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.data.repository.firebase_models.FirebaseLongTaskStat
import com.example.mywaycompose.data.repository.firebase_models.FirebaseMainTask
import com.example.mywaycompose.data.repository.firebase_models.toLongTaskStat
import com.example.mywaycompose.domain.model.LongTaskStat
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.model.toMainTask
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.utils.Constants
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetLongTasksStatFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    operator fun invoke(): Flow<Resource<List<LongTaskStat>>> = callbackFlow {

        trySend(Resource.Loading())
        try {
            val tasks = remoteServiceRepository.getAppData<FirebaseLongTaskStat>(Constants.LONG_TASKS_STAT_KIND)
            var open_flow: ProducerScope<Resource<List<LongTaskStat>>>? = null
            tasks.onEach {stat ->
                open_flow = this
                trySend(Resource.Success(stat.map {it.toLongTaskStat()}))
            }.launchIn(coroutineScope)

            awaitClose{open_flow!!.close()}

        }catch (e:Exception){
            trySend(Resource.Error(e.toString()))
        }


    }

}