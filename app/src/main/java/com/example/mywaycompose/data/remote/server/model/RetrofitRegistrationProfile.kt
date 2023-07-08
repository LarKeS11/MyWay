package com.example.mywaycompose.data.remote.server.model

data class RetrofitRegistrationProfile(
    val name:String,
    val birthday: RetrofitDateServer,
    val email:String,
    val password:String
)

