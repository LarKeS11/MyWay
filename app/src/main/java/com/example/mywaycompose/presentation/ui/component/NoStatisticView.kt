package com.example.mywaycompose.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.TitleMyWayColor
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun NoStatisticView(colors: ThemeColors) {
    Box(
       modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Have done nothing yet(",
            fontSize = 14.sp,
            fontFamily = monsterrat,
            fontWeight = FontWeight.Medium,
            color = colors.title_color
        )
    }
}