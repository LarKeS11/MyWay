package com.example.mywaycompose.presentation.ui.screen.auth_screen.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun AuthErrorComponent(error:String) {
    Text(
        text = error,
        fontSize = 12.sp,
        fontFamily = monsterrat,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Normal
    )
}