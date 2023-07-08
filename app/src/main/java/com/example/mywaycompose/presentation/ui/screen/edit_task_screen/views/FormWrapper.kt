package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.TaskStrokeColor
import com.example.mywaycompose.presentation.theme.ThemeColors

@Composable
fun FormWrapper(
    visibleStroke:Boolean = true,
    colors:ThemeColors,
    content:@Composable () -> Unit
) {

    Card(
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color =if(visibleStroke) colors.form_stroke_color else Color.Transparent),
        elevation = 0.dp
    ) {
        Column(
            Modifier
                .padding(start = 9.dp), verticalArrangement = Arrangement.Center) {
            content()
        }
    }

}