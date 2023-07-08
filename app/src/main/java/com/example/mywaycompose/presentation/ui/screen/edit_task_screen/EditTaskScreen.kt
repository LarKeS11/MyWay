package com.example.mywaycompose.presentation.ui.screen.edit_task_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.component.LoadingView
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.component.*
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views.EditTaskHeaderView
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views.GradeClickerView
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views.MainTasksSliderView
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views.SubTasksListView
import com.example.mywaycompose.presentation.ui.component.TaskFieldView
import com.example.mywaycompose.presentation.ui.screen.auth_screen.component.AuthErrorComponent
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views.TimeTaskFieldView


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun EditTaskScreen(
    navController: NavController,
    id:Int,
    viewModel: EditTaskViewModel = hiltViewModel(),
    colors:ThemeColors
) {
    remember {
        viewModel.getTask(id)
    }

    val state by viewModel.state.collectAsState()

    val taskStateValue by viewModel.taskState
    val mainTasksStateValue by viewModel.mainTasksState
    val subTasksStateValue by viewModel.subTasksState

    val timeStateValue by viewModel.timeFormState
    val taskFormStateValue by viewModel.taskFormState

    val loadFirstMainTask by viewModel.loadFirstMainTask

    val cardState = rememberPagerState()


    val hasBeenEdited by viewModel.hasBeenEdited.collectAsState()


    LaunchedEffect(mainTasksStateValue){
        if(loadFirstMainTask != null && mainTasksStateValue.tasks.isNotEmpty()){
            val maintask = mainTasksStateValue.tasks.filter { it.id == loadFirstMainTask }[0]
            cardState.scrollToPage(mainTasksStateValue.tasks.indexOf(maintask))
            viewModel.blockFirstMainTask()
        }
    }

    LaunchedEffect(hasBeenEdited){
        if(hasBeenEdited){
            navController.navigate(Screen.TasksScreen.withArgs(taskStateValue.task!!.date))
        }
    }

    LaunchedEffect(cardState) {

            snapshotFlow { cardState.currentPage }.collect { page ->
                if(page != 0) {
                    if (mainTasksStateValue.tasks.isNotEmpty()) {
                        viewModel.setMainTaskId(mainTasksStateValue.tasks[page].id!!)
                        viewModel.getSubTasks(mainTasksStateValue.tasks[page].id!!)
                    }
                }else{
                    viewModel.setMainTaskId(null)
                }
                viewModel.setSubtaskId(null)
        }

    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(colors.main_background)){

        if(state.activeDatePicker) {
            AlertRangeDatePickerComponent(
                onDismiss = {
                    viewModel.switchDatePickerActive(false)
                },
                onNegativeClick = {
                    viewModel.switchDatePickerActive(false)
                },
                onPositiveClick = {item, item2, bool ->
                    viewModel.switchDatePickerActive(false)
                    if(bool) viewModel.setRangeDates(item, item2)
                    else viewModel.setRangeDates(nullable = true)
                },
                currentDate = viewModel.getActuallyDate(),
                compare = {date -> viewModel.compareDate(date) },
                colors = colors
            )
        }

        if(taskStateValue.isLoading){
            LoadingView()
        }

        if(taskStateValue.task != null){

            remember {
                viewModel.setTaskGrade(taskStateValue.task!!.grade!!)
            }

            LazyColumn(modifier = Modifier.fillMaxSize()){


                item {
                    Box(Modifier.padding(vertical = 30.dp, horizontal = 16.dp)) {
                        EditTaskHeaderView(
                            callbackNav ={
                                viewModel.doneEdit()
                            },
                            callback = {
                                viewModel.switchDatePickerActive(true)
                            },
                            colors = colors
                        )
                    }
                }

                item {

                    if(state.firstRangeDate != null){
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "Selected period from ${state.firstRangeDate} to ${state.secondRangeDate}",
                                fontSize = 14.sp,
                                fontFamily = monsterrat,
                                fontWeight = FontWeight.Bold,
                                color = colors.title_color,
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Normal
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }


                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)) {
                        Text(
                            text = "Change",
                            fontSize = 18.sp,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.Bold,
                            color = colors.title_color,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }


                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                            TimeTaskFieldView(
                                hint = "00:00",
                                text = timeStateValue,
                                modifier = Modifier
                                    .width(53.dp)
                                    .height(35.dp)
                                    .padding(1.dp),
                                colors = colors
                            ){ viewModel.setTime(it) }
                            TaskFieldView(
                                hint = "task",
                                text = taskFormStateValue,
                                modifier = Modifier
                                    .width(265.dp)
                                    .height(35.dp)
                                    .padding(7.dp),
                                colors = colors
                            ){
                                viewModel.setTask(it)
                            }
                    }
                }
                item{
                    Box(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        AuthErrorComponent(error = state.error)
                    }
                }
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 16.dp)) {
                        Text(
                            text = "Goal",
                            fontSize = 18.sp,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.Bold,
                            color = colors.title_color,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal
                        )
                    }
                }
                item {
                    if(taskStateValue.isLoading){
                        LoadingView()
                    }
                    if(mainTasksStateValue.tasks.isNotEmpty()){
                        MainTasksSliderView(
                            tasks = mainTasksStateValue.tasks,
                            cardState = cardState,
                            selectedId = if(state.idMainTask == null) -1 else state.idMainTask!!,
                            colors = colors
                        )
                    }

                }

                item {
                    if(subTasksStateValue.isLoading){
                        LoadingView()
                    }
                    if(subTasksStateValue.subtasks.isNotEmpty() && cardState.currentPage != 0){
                        Spacer(modifier = Modifier.height(10.dp))
                        SubTasksListView(
                            tasks = subTasksStateValue.subtasks,
                            callback = {id ->
                                viewModel.setSubtaskId(id)
                            },
                            colors = colors
                        )
                    }
                }
                item {
                    Column(modifier = Modifier.padding(bottom = 30.dp)) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Task priority:",
                                fontSize = 18.sp,
                                fontFamily = monsterrat,
                                fontWeight = FontWeight.Bold,
                                color = colors.title_color,
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Normal
                            )
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            GradeClickerView(
                                callback = {
                                    viewModel.setTaskGrade(state.taskGrade + it)
                                },
                                numChange = {
                                    viewModel.setTaskGrade(it.toInt())
                                },
                                number = state.taskGrade,
                                colors = colors
                            )
                        }
                    }

                }


            }
        }
        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 25.dp, bottom = 44.dp)) {
            IconButton(
                onClick = {
                          viewModel.setupUpdateTask()
            }, modifier = Modifier.size(50.dp)) {
                Icon(painter = painterResource(
                    id = R.drawable.plus_icon_cirlce),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp),
                    tint = colors.icons
                )
            }
        }

    }

}