package com.example.mywaycompose.presentation.ui.screen.tasks_screen.state

data class TasksState(
    val actuallyDate:String? = null,
    val daysValuesList:List<Pair<String, String>> = listOf(),
    val showAddTaskForm:Boolean = false,
    val formTaskError:String = ""
)