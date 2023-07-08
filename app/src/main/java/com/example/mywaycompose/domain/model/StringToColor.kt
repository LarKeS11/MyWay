package com.example.mywaycompose.domain.model

import android.util.Log
import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import com.example.mywaycompose.presentation.theme.darkenColor

fun String.toColor(): Color {

    val parse = this.split(";")

    return Color(
        blue = (parse[1].filter { it != '.' }).toInt(),
        red = (parse[0].filter { it != '.' }).toInt(),
        green = (parse[2].filter { it != '.' }).toInt(),
        alpha = (parse[3].filter { it != '.' }).toInt()
    )

}

fun String.toColorWithShade():Color{
    val parse = this.split(";")
    Log.d("drgdffdfgdfg","${parse[0].toFloat()} ${parse[1].toFloat()} ${parse[2].toFloat()} ${parse[3].toFloat()}")
    return Color(
        blue = parse[1].toFloat(),
        red = parse[0].toFloat(),
        green = parse[2].toFloat(),
        alpha = parse[3].toFloat()
    ).copy(blue = 0.0002f)
}

fun String.toDarknesColor():Color{
    val parse = this.split(";")


    return Color(
        blue = darkenColor((parse[1].filter { it != '.' }).toInt()),
        red = darkenColor((parse[0].filter { it != '.' }).toInt()),
        green = darkenColor((parse[2].filter { it != '.' }).toInt()),
        alpha = (parse[3].filter { it != '.' }).toInt()
    )

}