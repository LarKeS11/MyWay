package com.example.mywaycompose.domain.usecase.statistics

import android.util.Log
import com.example.mywaycompose.data.repository.firebase_models.FirebaseTaskStat
import com.example.mywaycompose.data.repository.firebase_models.toTaskStatistic
import com.example.mywaycompose.data.repository.statistics.DataRemoteStatisticsRepository
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository
import com.example.mywaycompose.utils.Constants.TASK_STAT_KIND
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetTasksStatisticsFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    operator fun invoke():Flow<Resource<List<TaskStatistic>>> = callbackFlow {

        trySend(Resource.Loading())
        try {
            val stats = remoteServiceRepository.getAppData<FirebaseTaskStat>(TASK_STAT_KIND)
            var open_flow: ProducerScope<Resource<List<TaskStatistic>>>? = null
            stats.onEach {stats ->
                Log.d("sfdbfsfasdbawfegb","wefg")
                open_flow = this
                trySend(Resource.Success(stats.map { it.toTaskStatistic() }))
            }.launchIn(coroutineScope)

            awaitClose{open_flow!!.close()}

        }catch (e:Exception){
            Log.d("fsdfgsdfg",e.toString())
            trySend(Resource.Error(e.toString()))
        }

    }

}