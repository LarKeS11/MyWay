package com.example.mywaycompose.data.remote.server.api

import com.example.mywaycompose.data.remote.server.model.RetrofitLoginData
import com.example.mywaycompose.data.remote.server.model.RetrofitRegistrationProfile
import com.example.mywaycompose.data.remote.server.model.RetrofitResponse
import com.example.mywaycompose.data.remote.server.model.RetrofitSessionResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.net.ResponseCache

interface MyWayApi {

    @Headers("Content-Type: application/json")
    @POST("/authorization/registed")
    suspend fun registrationUser(
        @Body registrationProfile: RetrofitRegistrationProfile
    ): Response<RetrofitSessionResponse>

    @Multipart
    @PUT("/profile/upload-file")
    suspend fun uploadProfileImage(
        @Query("session") session:String,
        @Part("file\"; filename=\"pp.png\" ") file: RequestBody
    ):Response<RetrofitResponse>

    @Headers("Content-Type: application/json")
    @POST("/authorization/login")
    suspend fun authorizationUser(
        @Body loginData: RetrofitLoginData
    ):Response<RetrofitSessionResponse>
}