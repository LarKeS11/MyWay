package com.example.mywaycompose.domain.model

data class TaskStatistic(
    var id:Int? = null,
    val date:String,
    val priority:Int,
    val mainTaskId:Int
)