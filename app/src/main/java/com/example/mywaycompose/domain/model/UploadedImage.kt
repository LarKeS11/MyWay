package com.example.mywaycompose.domain.model

import java.io.File

data class UploadedImage(
    val session:String,
    val file: File,
    val type:String
)