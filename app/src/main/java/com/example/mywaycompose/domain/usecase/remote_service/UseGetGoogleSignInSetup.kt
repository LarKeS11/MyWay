package com.example.mywaycompose.domain.usecase.remote_service

import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.utils.Resource
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseGetGoogleSignInSetup @Inject constructor(
    private val remoteServiceRepository: RemoteServiceRepository
) {

    operator fun invoke(): Flow<Resource<GoogleSignInClient>> = flow {
        emit(Resource.Loading())
        try {
          val res = remoteServiceRepository.googleAuthRequest()
          emit(Resource.Success(res))
        }catch (e:Exception){

        }

    }

}