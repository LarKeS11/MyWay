package com.example.mywaycompose.data.repository.firebase_models

import com.example.mywaycompose.domain.model.LongTaskStat
import com.google.gson.annotations.SerializedName

data class FirebaseLongTaskStat(
    @field:SerializedName("id_task")
    val id_task: Int? = null,
    @field:SerializedName("date")
    val date: String? = null
)

fun FirebaseLongTaskStat.toLongTaskStat() = LongTaskStat(
    idTask = id_task!!,
    date = date!!
)

fun LongTaskStat.toFirebaseLongTaskStat() = FirebaseLongTaskStat(
    id_task = idTask,
    date =  date
)
