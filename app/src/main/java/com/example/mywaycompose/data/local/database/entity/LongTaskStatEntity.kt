package com.example.mywaycompose.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LongTaskStatEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    val idTask:Int,
    val date:String
)