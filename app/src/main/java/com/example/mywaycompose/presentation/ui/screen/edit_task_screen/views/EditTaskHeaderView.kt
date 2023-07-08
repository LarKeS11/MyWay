package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.theme.IconColor
import com.example.mywaycompose.presentation.theme.TasksTitleColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun EditTaskHeaderView(
    colors:ThemeColors,
    callbackNav: () -> Unit,
    callback:() -> Unit
) {

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row() {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "New task",
                fontSize = 20.sp,
                fontFamily = monsterrat,
                fontWeight = FontWeight.Bold,
                color = colors.title_color,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal
            )
        }
        Row() {
            IconButton(
                onClick = {
                    callback()
               },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.calendar_icon),
                    tint = colors.icons,
                    modifier = Modifier.size(30.dp),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.width(15.dp))
            IconButton(onClick = {
                callbackNav()
            }, modifier = Modifier.size(30.dp)) {
                Icon(
                    painterResource(id = R.drawable.close_circle),
                    tint = colors.icons,
                    modifier = Modifier.size(30.dp),
                    contentDescription = ""
                )
            }
        }
    }

}