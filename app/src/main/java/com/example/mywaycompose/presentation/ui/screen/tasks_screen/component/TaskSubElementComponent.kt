package com.example.mywaycompose.presentation.ui.screen.tasks_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.TaskSubItemColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun TaskSubElementComponent(
    icon:Int,
    title:String,
    color: Color,
    colors:ThemeColors,
    modifier: Modifier = Modifier,
    callback:(() -> Unit)? = null
) {


    Button(
        onClick = { if(callback != null) callback() },
        elevation = ButtonDefaults.elevation(0.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier.height(30.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = color
            )
            if(title.isNotEmpty()) {
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = title,
                    color = colors.title_color,
                    fontSize = 11.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}