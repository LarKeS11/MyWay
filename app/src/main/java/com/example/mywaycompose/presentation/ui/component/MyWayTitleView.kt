package com.example.mywaycompose.presentation.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.TasksTitleColor
import com.example.mywaycompose.presentation.theme.monsterrat


@Composable
fun MyWayTitleView(title:String, size:Int) {
    Text(
        text = title,
        fontSize = size.sp,
        fontFamily = monsterrat,
        fontWeight = FontWeight.Bold,
        color = TasksTitleColor,
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Normal
    )
}