package com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen.state

import com.example.mywaycompose.domain.model.MainTask

data class MainTasksState(
    val isLoading:Boolean = false,
    val success:List<MainTask> = listOf(),
    val error:String = ""
)
