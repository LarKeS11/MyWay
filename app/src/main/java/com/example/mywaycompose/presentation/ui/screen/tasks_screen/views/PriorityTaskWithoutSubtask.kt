package com.example.mywaycompose.presentation.ui.screen.tasks_screen.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.component.LoadingView
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.component.OptionButtonsBar
import com.example.mywaycompose.utils.Constants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PriorityTaskWithoutSubtask(
    task: Task,
    callbackEdit:() -> Unit = {},
    callbackToIdeas:() -> Unit = {},
    callbackCheck:() -> Unit = {},
    callbackToRemove:() ->  Unit = {},
    callbackToComplete:() -> Unit = {},
    colors: ThemeColors,
    longTaskProgress:Float = 0f,
    showPrimaryText:Boolean = true,
    timeTextFontSize:Int = 16,
    optionKind:String = Constants.TASK_OPTION_BUTTONS
) {

    val showOptionsState = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Constants.TASKS_HORIZONTAL_PADDINGS)
            .padding(vertical = 3.dp)
            .combinedClickable(
                onClick = {
                    if (!showOptionsState.value) callbackEdit()
                    showOptionsState.value = false
                },
                onLongClick = { showOptionsState.value = true },
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 71.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = 2.dp,
            backgroundColor = colors.task_background_color,

            ) {
            Box(modifier = Modifier.fillMaxWidth()){
                Column(
                    modifier = Modifier.background(colors.main_background)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colors.task_background_color)
                            .padding(end = 5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .width(220.dp)
                                .drawBehind {

                                    val path = Path().apply {
                                        addRoundRect(
                                            RoundRect(
                                                rect = Rect(
                                                    offset = Offset(0f, 0f),
                                                    size = Size(size.width / 2f, size.height),
                                                ),
                                                bottomRight = CornerRadius(100f, 125f),
                                            )
                                        )
                                    }

                                    drawPath(path, color = colors.simple_task_subground)

                                }

                        ) {
                            Box(
                                Modifier
                                    .padding(start = 18.dp, end = 23.dp)
                                    .padding(vertical = 13.dp)
                                    .fillMaxWidth()) {
                                Column{
                                    Text(
                                        text = task.time,
                                        color = colors.title_color,
                                        fontSize = timeTextFontSize.sp,
                                        fontFamily = monsterrat,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = task.task,
                                        color = colors.title_color,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = monsterrat
                                    )
                                }
                                if(showPrimaryText){
                                    Box(modifier = Modifier.padding(start = 65.dp, top = 2.dp)) {
                                        Text(
                                            text = task.mainTaskTitle!!,
                                            color = colors.title_color,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            fontFamily = monsterrat
                                        )
                                    }
                                }
                            }
                        }
                        Box(modifier = Modifier
                            .width(116.dp)
                            .height(65.dp)
                            .clip(RoundedCornerShape(12.dp))
                        ) {
                            AsyncImage(
                                model = task.mainTaskImage,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    if(showOptionsState.value){
                        OptionButtonsBar(
                            callbackToIdeas = {
                                callbackToIdeas()
                            },
                            callbackRemove = {
                                callbackToRemove()
                            },
                            callbackToComplete = {
                                callbackToComplete()
                            },
                            callbackCheck = {callbackCheck()},
                            progress = longTaskProgress,
                            colors = colors,
                            kind = optionKind
                        )
                    }
                }
                GradeView(
                    colors = colors,
                    num = task.grade!!
                )
            }
        }


    }

}