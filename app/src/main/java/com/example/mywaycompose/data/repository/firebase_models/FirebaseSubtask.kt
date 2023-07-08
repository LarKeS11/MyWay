package com.example.mywaycompose.data.repository.firebase_models

import com.example.mywaycompose.domain.model.SubTask
import com.google.gson.annotations.SerializedName

data class FirebaseSubtask(
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("color")
    val color: String? = null,
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("mainTaskId")
    val mainTaskId: Int? = null,
)

fun FirebaseSubtask.toSubtask(): SubTask {
    return SubTask(
        id = this.id,
        title = this.title!!,
        color = this.color!!,
        mainTaskId = this.mainTaskId!!
    )
}

fun SubTask.toResponseFirebaseSubtask():FirebaseSubtask{
    return FirebaseSubtask(
        id = this.id,
        title = this.title,
        color = this.color,
        mainTaskId = this.mainTaskId!!
    )
}