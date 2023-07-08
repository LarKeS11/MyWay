package com.example.mywaycompose.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubTaskEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val title:String,
    val color:String,
    val mainTaskId:Int
)