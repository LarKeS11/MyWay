package com.example.mywaycompose.domain.model

import com.example.mywaycompose.data.repository.firebase_models.FirebaseMainTask
import java.io.Serializable

data class MainTask(
    var id:Int? = null,
    val title:String,
    val icon:String,
    var imageSrc:String,
    var doubts:Boolean = false,
    var idIdea:Int? = null
): Serializable

fun FirebaseMainTask.toMainTask():MainTask{
    return MainTask(
        id = this.id!!,
        title = this.title!!,
        icon = this.icon!!,
        imageSrc = "",
        doubts = this.doubts
    )
}

fun MainTask.toFirebaseMainTask():FirebaseMainTask{
    return FirebaseMainTask(
        id = this.id,
        title = this.title,
        icon = this.icon,
        doubts = this.doubts
    )
}