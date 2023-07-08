package com.example.mywaycompose.domain.model

import com.example.mywaycompose.data.remote.server.model.RetrofitDateServer

data class RegistrationProfile(
    val name:String,
    val birthday: DateServer,
    val email:String,
    val password:String
)