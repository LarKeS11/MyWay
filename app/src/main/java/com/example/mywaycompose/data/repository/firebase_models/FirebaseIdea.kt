package com.example.mywaycompose.data.repository.firebase_models

import com.example.mywaycompose.domain.model.Idea
import com.google.gson.annotations.SerializedName

data class FirebaseIdea(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("idea")
    val idea: String? = null
)

fun FirebaseIdea.toIdea():Idea{
    return Idea(
        id = id,
        idea = idea!!
    )
}

fun Idea.toResponseFirebaseIdea():FirebaseIdea{
    return FirebaseIdea(
        id = id,
        idea = idea
    )
}
