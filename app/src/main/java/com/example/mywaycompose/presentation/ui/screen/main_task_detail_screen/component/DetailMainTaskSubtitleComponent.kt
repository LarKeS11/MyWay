package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun DetailMainTaskSubtitleComponent(title:String, size:Int) {
    Text(
        text = title,
        fontFamily = monsterrat,
        fontWeight = FontWeight.Medium,
        fontSize = size.sp,
        color = Color.White
    )
}