package com.example.mywaycompose.presentation.theme

import android.graphics.Color
import androidx.annotation.ColorInt

@ColorInt fun darkenColor(@ColorInt color: Int): Int {
    return Color.HSVToColor(FloatArray(3).apply {
        Color.colorToHSV(color, this)
        this[2] *= 0.7f
    })
}