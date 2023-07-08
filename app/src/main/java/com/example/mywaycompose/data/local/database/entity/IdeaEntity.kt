package com.example.mywaycompose.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IdeaEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    val idea:String
)