package com.example.mywaycompose.presentation.ui.screen.tasks_screen.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.R
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.component.OptionButtonsBar
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.component.TaskSubElementComponent
import com.example.mywaycompose.utils.Constants
import com.example.mywaycompose.utils.Constants.TASKS_HORIZONTAL_PADDINGS

@OptIn(ExperimentalFoundationApi::class, ExperimentalTextApi::class)
@Composable
fun TaskView(
    task:Task,
    editTask:() -> Unit = {},
    callbackToRemove:() ->  Unit = {},
    callbackToComplete:() -> Unit = {},
    callbackCheck:() -> Unit = {},
    timeTextFontSize:Int = 16,
    callbackToIdeas:() -> Unit = {},
    longTaskProgress:Float = 0f,
    optionKind:String = Constants.TASK_OPTION_BUTTONS,
    colors:ThemeColors
) {


    val textMeasurer = rememberTextMeasurer()
    val showOptionsState = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TASKS_HORIZONTAL_PADDINGS)
            .padding(vertical = 3.dp)
            .combinedClickable(
                onClick = {
                    if (!showOptionsState.value) editTask()
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

            Column(
                modifier = Modifier.background(colors.main_background)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.task_background_color)
                        .drawBehind {

                            val textSize = textMeasurer.measure(text = AnnotatedString(task.task))
                            val path = Path().apply {
                                addRoundRect(
                                    RoundRect(
                                        rect = Rect(
                                            offset = Offset(0f, 0f),
                                            size = Size(size.width / 3f, size.height),
                                        ),
                                        bottomRight = CornerRadius(100f, 125f),
                                    )
                                )
                            }

                            drawPath(path, color = colors.simple_task_subground)

                        }

                ) {

                    Column(
                        Modifier
                            .padding(start = 26.dp, end = 23.dp)
                            .padding(bottom = 13.dp)
                            .padding(top = 18.dp)
                            .fillMaxWidth()
                    ) {
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

        }

        GradeView(
            colors = colors,
            num = task.grade!!
        )

    }


}

