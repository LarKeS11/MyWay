package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.theme.TasksTitleColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun MainTaskDropDownMenuView(
    colors:ThemeColors,
    callbackEdit:() -> Unit,
    callbackDelete:() -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(colors.main_background)
            .padding(15.dp)
    ) {
        Button(
            onClick = {
            callbackEdit()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(
                text = "Edit",
                fontSize = 12.sp,
                fontFamily = monsterrat,
                fontWeight = FontWeight.Bold,
                color = colors.title_color,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
            callbackDelete()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(
                text = "Remove",
                fontSize = 12.sp,
                fontFamily = monsterrat,
                fontWeight = FontWeight.Bold,
                color = colors.title_color,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal
            )
        }
    }
}