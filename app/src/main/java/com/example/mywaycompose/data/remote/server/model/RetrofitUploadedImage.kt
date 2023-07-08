package com.example.mywaycompose.data.remote.server.model

import java.io.File

data class RetrofitUploadedImage(
    val session:String,
    val file:File,
    val type:String
)