package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.SelectedMainTaskTitleColor
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun SelectedMainTaskTitleComponent(title:String) {
    Text(
        text = title,
        color = SelectedMainTaskTitleColor,
        fontFamily = monsterrat,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
}