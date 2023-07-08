package com.example.mywaycompose.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MainTaskEntity(
    @PrimaryKey(autoGenerate = false)
    var id:Int?,
    val title:String,
    val icon:String,
    val imageSrc:String,
    var doubts:Boolean = false,
    var idIdea:Int? = null
)