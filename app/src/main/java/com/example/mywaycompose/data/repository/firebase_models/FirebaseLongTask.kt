package com.example.mywaycompose.data.repository.firebase_models

import com.example.mywaycompose.domain.model.LongTask
import com.google.firebase.ktx.Firebase
import com.google.gson.annotations.SerializedName

data class FirebaseLongTask(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("task")
    val task: String? = null,
    @field:SerializedName("idMainTask")
    val idMainTask: Int? = null,
    @field:SerializedName("idSubtask")
    val idSubtask: Int? = null,
    @field:SerializedName("startDate")
    val startDate: String? = null,
    @field:SerializedName("mainTaskImage")
    val mainTaskImage: String? = null,
    @field:SerializedName("subtaskTitle")
    val subtaskTitle: String? = null,
    @field:SerializedName("subtaskColor")
    val subtaskColor: String? = null,
    @field:SerializedName("mainTaskTitle")
    val mainTaskTitle: String? = null,
    @field:SerializedName("endDate")
    val endDate: String? = null
)

fun FirebaseLongTask.toLongTask():LongTask{
    return LongTask(
        id = id,
        task = task!!,
        idMainTask = idMainTask,
        idSubtask = idSubtask,
        startDate = startDate,
        endDate = endDate,
        mainTaskImage = mainTaskImage,
        subtaskTitle = subtaskTitle,
        subtaskColor = subtaskColor,
        mainTaskTitle = mainTaskTitle
    )
}

fun LongTask.toFirebaseLongTask():FirebaseLongTask{
    return FirebaseLongTask(
        id = id,
        task = task,
        idMainTask = idMainTask,
        idSubtask = idSubtask,
        startDate = startDate,
        endDate = endDate,
        mainTaskImage = mainTaskImage,
        subtaskTitle = subtaskTitle,
        subtaskColor = subtaskColor,
        mainTaskTitle = mainTaskTitle
    )
}