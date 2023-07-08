package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.state

import com.example.mywaycompose.domain.model.MainTask

data class MainTasksState(
    val tasks:List<MainTask> = emptyList(),
    val isLoading:Boolean = false,
    val error:String = ""
)