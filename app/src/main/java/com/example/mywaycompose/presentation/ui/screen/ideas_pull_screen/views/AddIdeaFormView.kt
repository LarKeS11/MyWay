package com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.HintTextFieldColor
import com.example.mywaycompose.presentation.theme.TaskColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.UsuallyTaskColor
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.component.TaskFieldView

@Composable
fun AddIdeaFormView(
    taskListener:(String) -> Unit,
    task:String,
    colors:ThemeColors,
    submitCallback:() -> Unit
) {

    val focusRequester = remember { FocusRequester() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 65.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        backgroundColor = colors.task_background_color
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            TaskFieldView(
                hint = "Write your task",
                text = task,
                colors = colors,
                visibleStroke = false,
                textHintTextStyle = TextStyle(
                    color = colors.form_text_color,
                    fontWeight = FontWeight.Medium,
                    fontFamily = monsterrat,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                ),
                onDoneListener = {
                    submitCallback()
                }
            ) {
                Log.d("fgdsfsdfsdf","sfsdfsdf")
                taskListener(it)
            }
        }
    }
}