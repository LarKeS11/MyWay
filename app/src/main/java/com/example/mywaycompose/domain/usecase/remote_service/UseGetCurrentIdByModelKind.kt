package com.example.mywaycompose.domain.usecase.remote_service

import android.util.Log
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetCurrentIdByModelKind(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    operator fun invoke(kind:String):Flow<Resource<Int>> = callbackFlow {

        try {
            val id = remoteServiceRepository.getCurrentIdBySomeModel(kind)
            var open_flow: ProducerScope<Resource<Int>>? = null
            id.onEach {
                open_flow = this
                trySend(Resource.Success(it))
            }.launchIn(coroutineScope)

            awaitClose{open_flow!!.close()}

        }catch (e:Exception){
            Log.d("fsdfgsdfg",e.toString())
            trySend(Resource.Error(e.toString()))
        }

    }

}