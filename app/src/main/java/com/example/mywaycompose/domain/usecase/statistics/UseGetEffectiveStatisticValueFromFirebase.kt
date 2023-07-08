package com.example.mywaycompose.domain.usecase.statistics

import android.util.Log
import com.example.mywaycompose.data.repository.service.DataRemoteServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository
import com.example.mywaycompose.utils.Constants.EFFECTIVE_STAT_KIND
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetEffectiveStatisticValueFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    fun execute(): Flow<Resource<Int>> = callbackFlow {

        var open_flow: ProducerScope<Resource<Int>>? = null

        remoteServiceRepository.getAppData<Int>(EFFECTIVE_STAT_KIND).onEach {
            open_flow = this
            Log.d("fsdfgsdf",it.toString())
            trySend(Resource.Success(it[0]))
            this.close()
        }.launchIn(coroutineScope)
        awaitClose{open_flow!!.close()}
    }

}