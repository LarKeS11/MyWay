package com.example.mywaycompose.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskStatisticEntity(
    @PrimaryKey(autoGenerate = false)
    var id:Int? = null,
    val date:String,
    val priority:Int,
    val mainTaskId:Int
)