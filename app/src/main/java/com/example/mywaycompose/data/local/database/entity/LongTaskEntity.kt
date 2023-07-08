package com.example.mywaycompose.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LongTaskEntity(
    @PrimaryKey(autoGenerate = false)
    var id:Int? = null,
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