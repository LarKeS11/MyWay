package com.example.mywaycompose.presentation.ui.screen.tasks_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.model.toDateServer
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.component.MyWayTopAppBar
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.views.PriorityTaskWithoutSubtask
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.views.CompletedTaskView
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.views.LongTaskView
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.views.PrimaryTaskView
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.views.SwipeableTaskView
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.views.TaskView
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.views.TaskInfoFieldView
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.views.NavDatesListView
import com.example.mywaycompose.utils.Constants.TASK_OPTION_BUTTONS
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState




@Composable
fun TasksScreen(
    date:String? = null,
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel(),
    colors: ThemeColors
) {
    val state by viewModel.state.collectAsState()
    val editTask by viewModel.editTask.collectAsState()

    val tasksListState = viewModel.tasksListState
    val longTasksListState = viewModel.longTasksList
    val dialogState = rememberMaterialDialogState()

    LaunchedEffect(editTask){
        if(editTask != null) navController.navigate(Screen.EditTaskScreen.withArgs(editTask.toString()))
    }

    LaunchedEffect(Unit){
        if(date != null) viewModel.setActuallyDate(date)
    }



    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(colors.main_background)
    ){
        item {
            Column() {
                MyWayTopAppBar(
                    title = "MyWay",
                    showCalendarIcon = true,
                    showPlusIcon = true,
                    plusCallback = {
                        viewModel.switchShowAddTaskForm()
                    },
                    calendarCallback = {
                        dialogState.show()
                    },
                    profileIconCallback = {
                        navController.navigate(Screen.SwitchAppThemeScreen.route)
                    },
                    image = viewModel.authSession!!.photoUrl!!,
                    colors = colors
                )

                Spacer(modifier = Modifier.height(8.dp))
                if(state.actuallyDate != null) {
                    NavDatesListView(
                        days = state.daysValuesList,
                        toSelect = { day ->
                            viewModel.setActuallyDate(day)
                        },
                        initialIndex = state.actuallyDate!!.toDateServer().day.toInt() - 1,
                        colors = colors
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            MaterialDialog(
                dialogState = dialogState,
                buttons = {
                    positiveButton(
                        text = "Ok",
                        textStyle = TextStyle(color = SelectedDateCalendarColor)
                    )
                    negativeButton(
                        text = "Cancel",
                        textStyle = TextStyle(color = SelectedDateCalendarColor)
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
                    viewModel.setActuallyDate("${if(date.dayOfMonth < 10) "0${date.dayOfMonth}" else date.dayOfMonth.toString()}-${if(date.monthValue.toString().length==1)"0${date.monthValue}" else date.monthValue.toString()}-${date.year}")
                }
            }
        }
        itemsIndexed(longTasksListState) { index, item ->
            Box(Modifier.padding(horizontal = 0.dp)) {
                LongTaskView(
                    task = item,
                    callbackGetPoints = { state ->
                        viewModel.getPointOfLongTask(item, state)
                    },
                    callbackDeleteTask = { task ->
                        viewModel.deleteLongTask(task)
                    },
                    callbackCheck = {
                        viewModel.checkLongTask(it)
                    },
                    colors = colors,
                    active = viewModel.selectedDateEqualsActually()
                )
            }
        }

        item {
            if(state.showAddTaskForm){
                TaskInfoFieldView(
                    addTask = {task ->
                        viewModel.addTask(
                            Task(
                                task = task.second,
                                time = task.first,
                                date = state.actuallyDate!!,
                                status = false,
                                grade = 0
                            )
                        )
                    },
                    error = state.formTaskError,
                    colors = colors
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        items(tasksListState.filter { !it.status },key = {it.id!!}){task ->
            SwipeableTaskView(
                deleteCallback = {
                    viewModel.deleteTask(task)
                },
                toCompleteCallback = {
                    viewModel.toCompleteTask(task)
                },
                block = !viewModel.actualDay()
            ){
                if(task.idBigTask == null) TaskView(
                    task = task,
                    callbackToIdeas = {viewModel.taskToIdea(task)},
                    editTask = {viewModel.toEditTask(task.id!!)},
                    colors = colors
                )
                else if(task.idSubTask != null) PrimaryTaskView(
                    task = task,
                    callbackEdit = {
                        viewModel.toEditTask(task.id!!)
                    },
                    callbackRemove = {
                        viewModel.deleteTask(task)
                    },
                    callBackComplete = {
                        viewModel.toCompleteTask(task)
                    },
                    callbackToIdeas = {
                        viewModel.taskToIdea(task)
                    },
                    optionKind = TASK_OPTION_BUTTONS,
                    colors = colors
                )
                else PriorityTaskWithoutSubtask(
                    task = task,
                    colors = colors,
                    callbackEdit = {
                        viewModel.toEditTask(task.id!!)
                    },
                    callbackToIdeas = {
                        viewModel.taskToIdea(task)
                    }
                )
            }
        }


        items(tasksListState.filter { it.status }, key = {it.id!!}){ task ->
            CompletedTaskView(task = task, colors = colors){
                viewModel.deleteTask(task)
            }
        }

        item {
            Spacer(modifier = Modifier.height(150.dp))
        }

    }
}