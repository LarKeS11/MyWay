package com.example.mywaycompose.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Idea(
    val id:Int? = null,
    val idea:String
): Serializable