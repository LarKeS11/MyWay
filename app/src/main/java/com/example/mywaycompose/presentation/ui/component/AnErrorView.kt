package com.example.mywaycompose.presentation.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun AnErrorView(
    error:String,
    size:Int
) {
   Text(
       text = error,
       fontSize = size.sp,
       fontFamily = monsterrat,
       fontWeight = FontWeight.Medium,
       fontStyle = FontStyle.Normal,
       color = Color.Red
   )
}