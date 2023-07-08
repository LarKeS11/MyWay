package com.example.mywaycompose.presentation.ui.screen.tasks_screen.state

import com.example.mywaycompose.domain.model.Task

data class LongTasksState(
    val isLoading:Boolean = false,
    val tasks:List<Task> = listOf()
)