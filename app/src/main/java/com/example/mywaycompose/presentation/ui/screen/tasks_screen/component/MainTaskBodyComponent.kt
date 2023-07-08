package com.example.mywaycompose.presentation.ui.screen.tasks_screen.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.model.toColor
import com.example.mywaycompose.presentation.theme.PriorityTaskTitleColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.TitleMainTaskInTaskColor
import com.example.mywaycompose.presentation.theme.gilory
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.state.MainTasksState
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.state.SubtaskTaskState

@Composable
fun MainTaskBodyComponent(
    task: Task,
    subtaskState:MutableState<SubtaskTaskState>,
    colors:ThemeColors,
    mainTaskState:MutableState<MainTask>,
    dates:Pair<String, String>? = null
) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(end = 23.dp)
                .padding(vertical = 11.dp)
        ) {
            Row {
                if (dates != null) {
                    Text(
                        text = "${dates.first} - ${dates.second}",
                        color = colors.title_color,
                        fontSize = 12.sp,
                        fontFamily = monsterrat,
                        fontWeight = FontWeight.Bold
                    )
                }
                else{
                    Text(
                        text = task.time,
                        color = colors.espacially_first_task_title,
                        fontSize = 16.sp,
                        fontFamily = monsterrat,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(29.dp))
                val subtask_title = (if(subtaskState.value.task != null) subtaskState.value.task!!.title else mainTaskState.value.title) + "                 "
                Row(){

                    Spacer(modifier = Modifier.width(10.dp))
                    Row(){
                        Text(
                            text = subtask_title.slice(0..3),
                            color = colors.espacially_first_task_title,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = gilory
                        )
                        Text(
                            text =subtask_title.slice(4 until subtask_title.length),
                            color = colors.espacially_second_task_title,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = gilory,
                            textAlign = TextAlign.Start
                        )
                    }

                }
            }

        }



}