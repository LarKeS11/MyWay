package com.example.mywaycompose.presentation.ui.screen.tasks_screen.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.R
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.component.TaskSubElementComponent
import com.example.mywaycompose.utils.Constants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompletedTaskView(
    task:Task,
    colors:ThemeColors,
    removeCallback:(Task) -> Unit

) {



    val showOptionsState = remember {
        mutableStateOf(false)
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Constants.TASKS_HORIZONTAL_PADDINGS)
            .padding(vertical = 3.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 71.dp)
                .combinedClickable(
                    onClick = {
                        showOptionsState.value = false
                    },
                    onLongClick = { showOptionsState.value = true },
                )
                .clip(RoundedCornerShape(12.dp))
                .background(colors.task_background_color.copy(alpha = 0.5f))

            ) {

            Column() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.task_background_color)
                        .drawBehind {
                            val path = Path().apply {
                                addRoundRect(
                                    RoundRect(
                                        rect = Rect(
                                            offset = Offset(0f, 0f),
                                            size = Size(size.width / 3f, size.height),
                                        ),
                                        bottomRight = CornerRadius(100f, 125f)
                                    )
                                )
                            }
                            drawPath(path, color = colors.simple_task_subground.copy(0.5f))
                        }
                ) {
                    Column(
                        Modifier
                            .padding(start = 18.dp, end = 23.dp)
                            .padding(vertical = 13.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = task.time,
                            color = colors.title_color.copy(alpha = 0.5f),
                            fontSize = 16.sp,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = task.task,
                            color = colors.title_color.copy(alpha = 0.5f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = monsterrat,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough)
                        )
                    }

//                    Box(
//                        modifier = Modifier
//                            .defaultMinSize(minHeight = 75.dp)
//                            .fillMaxWidth()
//                            .background(Color(0x1AFFFFFF))
//                    ) {
//
//                    }

                }
                if(showOptionsState.value){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TaskSubElementComponent(icon = R.drawable.trash_icon, title = "Remove", modifier = Modifier.fillMaxWidth(), color = colors.trash_color,colors = colors, callback = {
                            removeCallback(task)
                        })
                    }
                }
            }

        }
    }
}