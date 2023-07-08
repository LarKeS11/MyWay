package com.example.mywaycompose.data.repository.firebase_models

import com.example.mywaycompose.domain.model.TaskStatistic
import com.google.gson.annotations.SerializedName

data class FirebaseTaskStat(
    @field:SerializedName("date")
    val date: String? = null,
    @field:SerializedName("priority")
    val priority: Int? = null,
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("mainTaskId")
    val mainTaskId:Int? = null,

)

fun TaskStatistic.toResponseFirebaseTaskStat():FirebaseTaskStat{
    return FirebaseTaskStat(
        id = id,
        date = date,
        priority = priority,
        mainTaskId = mainTaskId
    )
}

fun FirebaseTaskStat.toTaskStatistic():TaskStatistic{
    return TaskStatistic(
        id = id,
        date = date!!,
        priority = priority!!,
        mainTaskId = mainTaskId!!
    )
}