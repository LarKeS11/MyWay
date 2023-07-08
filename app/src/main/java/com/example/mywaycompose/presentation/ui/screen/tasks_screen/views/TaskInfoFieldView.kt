package com.example.mywaycompose.presentation.ui.screen.tasks_screen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.TaskColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.ui.component.AnErrorView
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.component.TaskFieldComponent
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.component.TasksTimeFieldComponent
import com.example.mywaycompose.utils.Constants

@Composable
fun TaskInfoFieldView(
    addTask:(Pair<String, String>) -> Unit,
    error:String,
    colors:ThemeColors
) {

    val submittedTime = remember {
        mutableStateOf<String?>(null)
    }


    val timeState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val taskState = remember {
        mutableStateOf("")
    }

    fun submitTime(){

        submittedTime.value = ""
    }
    fun submitTask(){
        addTask(Pair(timeState.value.text.split(" ").filter { it.isNotEmpty() }.joinToString(separator = ":"), taskState.value))
    }

    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = Constants.TASKS_HORIZONTAL_PADDINGS)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 51.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            backgroundColor = colors.task_background_color
        ) {
            Column(
                Modifier
                    .padding(horizontal = 15.dp)
                    .padding(vertical = 16.dp)
            )
            {
                TasksTimeFieldComponent(timeState, ::submitTime, submittedTime.value == null, colors)
                if(error.isNotEmpty()) AnErrorView(error = error, size = 9)
                Spacer(modifier = Modifier.height(8.dp))
                TaskFieldComponent(taskState = taskState, submit = ::submitTask,toFocus = submittedTime.value != null, colors = colors)
            }
        }
    }


}