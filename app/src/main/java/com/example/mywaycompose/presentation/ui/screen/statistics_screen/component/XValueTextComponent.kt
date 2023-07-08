package com.example.mywaycompose.presentation.ui.screen.statistics_screen.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.StatisticsValueTextColor

import com.example.mywaycompose.presentation.theme.gilory

@Composable
fun XValueTextComponent(
    text:String
) {
    Text(
        text = text,
        fontFamily = gilory,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        color = StatisticsValueTextColor
    )
}