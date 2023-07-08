package com.example.mywaycompose.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    val task:String,
    val time:String,
    val date:String,
    val status:Boolean,
    val idBigTask:Int? = null,
    val idSubTask:Int? = null,
    val grade:Int? = null,
    val mainTaskImage:String? = null,
    val subtaskColor:String? = null,
    val mainTaskTitle:String? = null,
    val subtaskTitle:String? = null
)