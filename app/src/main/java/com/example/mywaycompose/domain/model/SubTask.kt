package com.example.mywaycompose.domain.model

data class SubTask(
    var id:Int? = null,
    val title:String,
    val color:String,
    val mainTaskId:Int
)