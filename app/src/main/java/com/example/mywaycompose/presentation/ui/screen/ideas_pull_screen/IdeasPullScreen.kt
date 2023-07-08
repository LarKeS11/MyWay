package com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen

import ChooseMainTaskAlertView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywaycompose.domain.model.DateServer
import com.example.mywaycompose.domain.model.toDateString
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.component.MyWayTopAppBar
import com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.views.AddSubtaskAlertFormView
import com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.views.IdeaCardView
import com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.views.AddIdeaFormView
import com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.views.TimePickerAlertView
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun IdeasPullScreen(
    navController: NavController,
    viewModel: PullIdeasViewModel = hiltViewModel(),
    colors:ThemeColors
) {

    val user = viewModel.user
    val state by viewModel.state.collectAsState()
    val showAlertDialog by viewModel.showAlertDialog.collectAsState()
    val mainTasks by viewModel.mainTasks.collectAsState()
    val subtaskMainTaskId by viewModel.subtaskMainTaskId.collectAsState()
    val currentIdea by viewModel.currentIdea.collectAsState()
    val showCalendarState = rememberMaterialDialogState()
    val showTimePicker by viewModel.showTimePicker.collectAsState()

    LaunchedEffect(state.mainTaskId){
        if(state.mainTaskId != null){
            navController.navigate(Screen.EditMainTaskScreen.withArgs(state.mainTaskId.toString(), "true"))
            viewModel.resetState()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.main_background)
            .padding(horizontal = 16.dp)
    ){

        item {
            if(showTimePicker){
                TimePickerAlertView(
                    error = state.timeError,
                    colors = colors,
                    onSubmit = {hours, minutes ->
                        viewModel.addTask(hours.toString(), minutes.toString())
                    }
                ) {
                    viewModel.switchShowingTimePicker()
                }
            }
        }

        item {
            MaterialDialog(
                dialogState = showCalendarState,
                buttons = {
                    positiveButton(
                        text = "Ok",
                        textStyle = TextStyle(color = colors.title_color)
                    )
                    negativeButton(
                        text = "Cancel",
                        textStyle = TextStyle(color = colors.title_color)
                    )
                },
                backgroundColor = colors.main_background,
                elevation = 0.dp

            ) {
                datepicker(
                    colors = DatePickerDefaults.colors(
                        headerBackgroundColor = colors.second_statistics,
                        dateActiveBackgroundColor = colors.selected_calendar_date,
                        dateActiveTextColor = colors.espacially_first_task_title,
                        headerTextColor = colors.title_color,
                        dateInactiveTextColor =  colors.title_color,
                        calendarHeaderTextColor = colors.title_color
                    ),

                    ) { date ->
                    viewModel.setTaskDay(
                        DateServer(
                            year = date.year.toString(),
                            month = if(date.monthValue.toString().length==1)"0${date.monthValue}" else date.monthValue.toString(),
                            day = if(date.dayOfMonth < 10) "0${date.dayOfMonth}" else date.dayOfMonth.toString()
                        ).toDateString()
                    )
                    viewModel.switchShowingTimePicker()
                }
            }
        }

        item { 
            if(showAlertDialog && subtaskMainTaskId == null){
                ChooseMainTaskAlertView(
                    mainTasks = mainTasks,
                    colors = colors,
                    onTap = {
                        viewModel.setSubTaskMainTaskId(it)
                    }
                ) {
                    viewModel.switchShowingAlertDialog()
                }
            }
            if(subtaskMainTaskId != null){
                AddSubtaskAlertFormView(
                    subtask = currentIdea!!,
                    onSubmit = {color, subtask ->
                        viewModel.addSubtask(color = color, title = subtask)
                    },
                    onDismiss = {
                        viewModel.setSubTaskMainTaskId(null)
                    },
                    colors = colors
                )
            }

        }
        item{
            MyWayTopAppBar(
                title = "Ideas collecton",
                image = user!!.photoUrl!!,
                showCalendarIcon = false,
                showPlusIcon = true,
                plusCallback = {
                    viewModel.switchFormTask()
                },
                colors = colors,
                profileIconCallback = {
                    navController.navigate(Screen.SwitchAppThemeScreen.route)
                }
            )
        }
        item {
            if(state.showTaskForm){
                Box(
                    modifier = Modifier.padding(top = 35.dp)
                ) {
                    AddIdeaFormView(
                        task = state.task,
                        submitCallback = {
                            viewModel.addIdea(state.task)
                        },
                        taskListener = {
                            viewModel.changeTaskValue(it)
                        },
                        colors = colors
                    )
                }
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(if(state.showTaskForm)7.dp else 35.dp))
        }
        
        items(state.ideas,{it.id!!}){idea ->
            IdeaCardView(
                idea = idea,
                colors = colors,
                onMainTaskClick = {
                    viewModel.addMainTask(idea)
                },
                onDeleteIdea = {
                    viewModel.deleteIdea(idea)
                },
                onSubtask = {
                    viewModel.setCurrentIdea(idea.idea)
                    viewModel.switchShowingAlertDialog()
                },
                onCalendar = {
                    viewModel.setCurrentIdea(idea.idea)
                    showCalendarState.show()
                }
            )
        }
        item {
            Box(modifier = Modifier.height(120.dp))
        }
    }

}