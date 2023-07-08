package com.example.mywaycompose.data.repository.firebase_models

import com.google.gson.annotations.SerializedName

data class FirebaseTask(


@field:SerializedName("date")
val date: String? = null,

@field:SerializedName("task")
val task: String? = null,

@field:SerializedName("idSubTask")
val idSubTask: Int? = null,

@field:SerializedName("idBigTask")
val idBigTask: Int? = null,

@field:SerializedName("time")
val time: String? = null,

@field:SerializedName("firstDatePeriod")
val firstDatePeriod: String? = null,

@field:SerializedName("secondDatePeriod")
val secondDatePeriod: String? = null,

@field:SerializedName("status")
val status: Boolean? = null,

@field:SerializedName("grade")
val grade: Int? = null,

@field:SerializedName("mainTaskImage")
val mainTaskImage: String? = null,

@field:SerializedName("subtaskColor")
val subtaskColor: String? = null,

@field:SerializedName("mainTaskTitle")
val mainTaskTitle: String? = null,

@field:SerializedName("subtaskTitle")
val subtaskTitle: String? = null

)

