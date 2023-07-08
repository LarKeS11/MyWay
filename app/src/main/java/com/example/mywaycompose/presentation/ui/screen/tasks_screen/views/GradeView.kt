package com.example.mywaycompose.presentation.ui.screen.tasks_screen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.TitleMyWayColor
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun GradeView(
    colors: ThemeColors,
    num:Int
) {
    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topEnd = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .background(colors.unselectedTabsBar)
            .padding(horizontal = 12.dp)


    ) {
        Text(
            text = num.toString(),
            color = colors.title_color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = monsterrat
        )
    }
}