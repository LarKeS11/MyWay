package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen

import ToggleButtonsView
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.component.*
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.component.BackArrowButtonComponent
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.views.MainTaskDropDownMenuView
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.views.SubtasksPreviewListView
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.views.TreeButtonView
import com.example.mywaycompose.utils.Constants.SecondStatistics
import java.io.File

@SuppressLint("RememberReturnType")
@Composable
fun MainTaskDetailScreen(
    navController: NavController,
    viewModel: MainTaskDetailViewModel = hiltViewModel(),
    colors:ThemeColors,
    id:Int
) {

    LaunchedEffect(Unit) {
        viewModel.getTaskById(id)
        viewModel.getTaskStatistic(id)
        viewModel.getSubtasks(id)
    }



    val mainTaskStateValue by viewModel.mainTaskDetailState
    val subtasksStateValue by viewModel.subtasksState
    val mainTaskStatistic by viewModel.mainTaskStatisticState
    val optionsMenuActive by viewModel.activeMainTaskOptionsState
    val toMainTasks by viewModel.toMainTasks
    val toEditMainTask by viewModel.toEditMainTask


    LaunchedEffect(toMainTasks){
        if(toMainTasks)navController.navigate(Screen.MainTasksListScreen.route)
    }
    LaunchedEffect(toEditMainTask){
        if(toEditMainTask) {
            viewModel.switchOptionsMenuActive()
            navController.navigate(
                Screen.EditMainTaskScreen.withArgs(
                    mainTaskStateValue.task!!.id.toString(),
                    "true"
                ))
        }
        viewModel.toEditMainTask(false)
    }


    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(colors.main_background)) {
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {

                if(mainTaskStateValue.isLoading){
                    LoadingView()
                }

                if(mainTaskStateValue.task != null){
                    Image(
                        painter = rememberImagePainter (data = File(mainTaskStateValue.task!!.imageSrc)),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .height(350.dp),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        colors.main_background,
                                        Color(0x8B3F3F)
                                    ),
                                    startY = 700f,
                                    endY = 0f,
                                    tileMode = TileMode.Clamp
                                )
                            )
                            .rotate(270f)
                            .align(Alignment.BottomEnd)
                    )


                    Box(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 20.dp)){
                        Text(
                            text = mainTaskStateValue.task!!.title,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 28.sp,
                            color = colors.title_color
                        )
                    }
                }

                Box(Modifier.padding(30.dp)) {
                    BackArrowButtonComponent(){
                        viewModel.toMainTasks()
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 30.dp, end = 20.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    EllipsisIconComponent(){
                        viewModel.switchOptionsMenuActive()
                    }
                    if(optionsMenuActive) {
                        MainTaskDropDownMenuView(
                            colors = colors,
                            callbackDelete = {
                                viewModel.deleteTask(mainTaskStateValue.task!!)
                                             },
                            callbackEdit = {
                                viewModel.toEditMainTask(true)
                            }
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(22.dp))
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                TreeButtonView(colors)
            }
            Spacer(modifier = Modifier.height(30.dp))
            if(subtasksStateValue.isLoading){
               LoadingView()
            }
        }

        item {
            Box(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "Subtasks:",
                    fontSize = 18.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Bold,
                    color = colors.title_color,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal
                )
                SubtasksPreviewListView(subtasksStateValue.tasks,colors)
            }
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Statistics:",
                    fontSize = 18.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Bold,
                    color = colors.title_color,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal
                )
                Spacer(modifier = Modifier.height(22.dp))
                ToggleButtonsView(kindStatistics = SecondStatistics,colors)
            }
            Spacer(modifier = Modifier.height(23.dp))
        }

        item {
            if(mainTaskStatistic.isLoading) LoadingView()
            if(!mainTaskStatistic.isLoading && mainTaskStatistic.stat.isEmpty()) NoStatisticView(colors)
            if(mainTaskStatistic.stat.isNotEmpty()) {
                Box(modifier = Modifier.height(250.dp)) {
                    BarChartView(data = mainTaskStatistic.stat, kind = SecondStatistics,colors = colors)
                }
            }
        }
        
        item { 
            Spacer(modifier = Modifier.height(30.dp))
        }

        
        
    }
}