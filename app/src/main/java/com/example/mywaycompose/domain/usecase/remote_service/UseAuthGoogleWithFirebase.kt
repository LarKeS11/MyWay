package com.example.mywaycompose.domain.usecase.remote_service

import com.example.mywaycompose.data.repository.service.DataRemoteServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseAuthGoogleWithFirebase(
    private val remoteServiceRepository: RemoteServiceRepository
) {

    operator fun invoke(idToken:String):Flow<Resource<com.google.android.gms.tasks.Task<AuthResult>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(remoteServiceRepository.firebaseAuthWithGoogle(idToken)))
        }catch (e:Exception){
            emit(Resource.Error(e.toString()))
        }
    }

}