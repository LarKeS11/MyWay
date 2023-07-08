package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.StrongButtonColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.component.DetailMainTaskSubtitleComponent

@Composable
fun TreeButtonView(
    colors:ThemeColors
) {
    Button(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colors.second_statistics),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Tasks Tree",
            fontFamily = monsterrat,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = colors.title_color
        )
    }
}