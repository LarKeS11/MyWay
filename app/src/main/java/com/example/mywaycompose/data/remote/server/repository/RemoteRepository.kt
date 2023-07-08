package com.example.mywaycompose.data.remote.server.repository

import android.util.Log
import com.example.mywaycompose.data.remote.server.api.MyWayApi
import com.example.mywaycompose.data.remote.server.model.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    val myWayApi: MyWayApi
) : InterfaceRemoteRepository {
    override suspend fun registrationUser(retrofitRegistrationProfile: RetrofitRegistrationProfile):Response<RetrofitSessionResponse> {
       return myWayApi.registrationUser(retrofitRegistrationProfile)
    }

    override suspend fun uploadProfileImage(userData: RetrofitUploadedImage): Response<RetrofitResponse> {
        val requestBody = RequestBody.create(
            userData.type.toMediaTypeOrNull(),userData.file)
        return myWayApi.uploadProfileImage(
            session = userData.session,
            file = requestBody
        )
    }

    override suspend fun authorizationUser(retrofitLoginData: RetrofitLoginData):Response<RetrofitSessionResponse> {
        return myWayApi.authorizationUser(retrofitLoginData)
    }
}