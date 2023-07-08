package com.example.mywaycompose.data.remote.server.repository

import com.example.mywaycompose.data.remote.server.model.*
import retrofit2.Response

interface InterfaceRemoteRepository {
    suspend fun registrationUser(retrofitRegistrationProfile: RetrofitRegistrationProfile):Response<RetrofitSessionResponse>
    suspend fun uploadProfileImage(userData: RetrofitUploadedImage):Response<RetrofitResponse>
    suspend fun authorizationUser(retrofitLoginData: RetrofitLoginData):Response<RetrofitSessionResponse>
}