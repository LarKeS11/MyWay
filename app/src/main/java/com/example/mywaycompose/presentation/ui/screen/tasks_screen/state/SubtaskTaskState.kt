package com.example.mywaycompose.presentation.ui.screen.tasks_screen.state

import com.example.mywaycompose.domain.model.SubTask

data class SubtaskTaskState(
 val isLoading:Boolean = false,
 val task:SubTask? = null
)