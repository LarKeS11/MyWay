package com.example.mywaycompose.data.repository.firebase_models

import com.google.gson.annotations.SerializedName

data class FirebaseMainTask(
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("icon")
    val icon: String? = null,
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("doubts")
    var doubts:Boolean = false
)
