package com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.presentation.theme.OpacityColor
import com.example.mywaycompose.presentation.theme.ThemeColors

@Composable
fun ClickableMainTaskView(
    mainTask:MainTask,
    colors: ThemeColors,
    callback:(Int) -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = OpacityColor),
        elevation = ButtonDefaults.elevation(0.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            callback(mainTask.id!!)
        }
    ) {

        MainTaskCardView(task = mainTask.title, image = mainTask.imageSrc, icon = mainTask.icon, colors = colors)
    }
}