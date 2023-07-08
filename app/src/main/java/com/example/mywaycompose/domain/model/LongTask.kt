package com.example.mywaycompose.domain.model

data class LongTask(
    val id:Int? = null,
    val task:String,
    val idMainTask:Int? = null,
    val idSubtask:Int? = null,
    val startDate:String? = null,
    val endDate:String? = null,
    val mainTaskImage:String? = null,
    val subtaskColor:String? = null,
    val mainTaskTitle:String? = null,
    val subtaskTitle:String? = null
)