package com.example.mywaycompose.domain.usecase.ideas

import android.util.Log
import com.example.mywaycompose.data.repository.firebase_models.FirebaseIdea
import com.example.mywaycompose.data.repository.firebase_models.toIdea
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.model.toTask
import com.example.mywaycompose.domain.repository.ideas.RemoteIdeasRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.utils.Constants.IDEAS_KIND
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UseGetIdeasFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    operator fun invoke(): Flow<Resource<List<Idea>>> = callbackFlow {

        trySend(Resource.Loading())
        try {
            val tasks = remoteServiceRepository.getAppData<FirebaseIdea>(IDEAS_KIND)
            var open_flow: ProducerScope<Resource<List<Idea>>>? = null
            tasks.onEach {
                open_flow = this
                trySend(Resource.Success(it.map { it.toIdea() }))
            }.launchIn(coroutineScope)

            awaitClose{open_flow!!.close()}

        }catch (e:Exception){
            trySend(Resource.Error(e.toString()))
        }


    }

}