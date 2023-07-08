package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mywaycompose.presentation.ui.component.MyWayTitleView

@Composable
fun LongTaskPreviewComponent(
    first:String,
    second:String
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        MyWayTitleView(title = "Selected period from $first to $second" , size = 14)
    }
}