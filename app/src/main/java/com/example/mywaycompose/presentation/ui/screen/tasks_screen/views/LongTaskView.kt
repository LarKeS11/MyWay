package com.example.mywaycompose.presentation.ui.screen.tasks_screen.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.state.LongTaskStatState
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.state.OneMainTaskState
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.state.SubtaskTaskState
import java.io.File
import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.toTask
import com.example.mywaycompose.utils.Constants
import com.example.mywaycompose.utils.Constants.LONG_TASK_OPTION_BUTTONS
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LongTaskView(
    task:LongTask,
    colors: ThemeColors,
    active:Boolean,
    callbackCheck:(Int) -> Unit,
    callbackGetPoints:(MutableState<Float>) -> Unit,
    callbackDeleteTask:(LongTask) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()



    val showFullTaskAndOptions = remember {
        mutableStateOf(Pair(false, false))
    }

    val longTaskStatState = remember {
        mutableStateOf(0f)
    }



    LaunchedEffect(Unit) {
        callbackGetPoints(longTaskStatState)
    }

    fun checkTask(){
        coroutineScope.launch {
            callbackCheck(task.id!!)
            delay(200)
            callbackGetPoints(longTaskStatState)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        if (showFullTaskAndOptions.value.first) {
            if (task.idMainTask != null && task.idSubtask != null) {
                PrimaryTaskView(
                    task = task.toTask(),
                    callbackEdit = {
                        if (showFullTaskAndOptions.value.second) showFullTaskAndOptions.value =
                            Pair(true, false)
                        else showFullTaskAndOptions.value = Pair(false, false)
                    },
                    callbackRemove = {
                        callbackDeleteTask(task)
                                     },
                    callBackComplete = {

                        callbackDeleteTask(task)
                    },
                    showPrimaryText = false,
                    callbackCheck = {
                        checkTask()
                    },
                    longTaskProgress = longTaskStatState.value,
                    optionKind = LONG_TASK_OPTION_BUTTONS,
                    timeTextFontSize = 13,
                    colors = colors
                )
            }
            if (task.idSubtask == null && task.idMainTask != null) {
                PriorityTaskWithoutSubtask(
                    task = task.toTask(),
                    callbackEdit = {
                        if (showFullTaskAndOptions.value.second) showFullTaskAndOptions.value =
                            Pair(true, false)
                        else showFullTaskAndOptions.value = Pair(false, false)
                    },
                    callbackCheck = {
                        checkTask()
                    },
                    callbackToRemove = {
                        callbackDeleteTask(task) }
                    ,
                    callbackToComplete = {
                        callbackDeleteTask(task)
                    },
                    longTaskProgress = longTaskStatState.value,
                    showPrimaryText = false,
                    optionKind = LONG_TASK_OPTION_BUTTONS,
                    timeTextFontSize = 13,
                    colors = colors
                )
            }
            if (task.idMainTask == null && task.idSubtask == null) {
                TaskView(
                    task = task.toTask(),
                    editTask = {
                        if (showFullTaskAndOptions.value.second) showFullTaskAndOptions.value =
                            Pair(true, false)
                        else showFullTaskAndOptions.value = Pair(false, false)
                    },
                    callbackCheck = {
                        checkTask()
                    },
                    callbackToRemove = { callbackDeleteTask(task) },
                    callbackToComplete = {
                        callbackDeleteTask(task)
                    },
                    longTaskProgress = longTaskStatState.value,
                    optionKind = LONG_TASK_OPTION_BUTTONS,
                    timeTextFontSize = 13,
                    colors = colors
                )
            }

        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Constants.TASKS_HORIZONTAL_PADDINGS)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = {
                                if (showFullTaskAndOptions.value.second) showFullTaskAndOptions.value =
                                    Pair(true, false)
                                else showFullTaskAndOptions.value = Pair(false, false)
                            },
                            onLongClick = {
                                if (active) {
                                    if (showFullTaskAndOptions.value.first) showFullTaskAndOptions.value =
                                        Pair(true, true)
                                    else showFullTaskAndOptions.value = Pair(true, false)
                                }

                            }
                        ),
                    backgroundColor = colors.task_background_color,
                    elevation = 2.dp
                ) {
                    Box(
                        modifier = Modifier
                            .height(33.dp)
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .background(colors.task_background_color)
                                .height(33.dp)
                        ) {
                            if(task.mainTaskImage != null) {
                                AsyncImage(
                                    model = task.mainTaskImage,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth(0.4f)
                                        .fillMaxHeight()
                                        .align(Alignment.BottomEnd),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if(task.task.length > 24)task.task.substring(0..24) + "..." else task.task,
                                    color = colors.title_color,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = gilory
                                )
                            }
                        }
                        if(!active) {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(colors.unselectedTabsBar.copy(alpha = 0.4f))
                            ) {

                            }
                        }
                    }

                }
            }
        }
    }
}


