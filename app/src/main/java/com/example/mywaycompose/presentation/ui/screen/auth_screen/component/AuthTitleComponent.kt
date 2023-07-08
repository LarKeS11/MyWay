package com.example.mywaycompose.presentation.ui.screen.auth_screen.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.AuthTitleColor
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun AuthTitleComponent(title:String) {

    Text(
        text = title,
        fontSize = 24.sp,
        fontFamily = monsterrat,
        fontWeight = FontWeight.Bold,
        color = AuthTitleColor,
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Normal
    )

}