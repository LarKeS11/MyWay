package com.example.mywaycompose.presentation.ui.screen.tasks_screen.state

import com.example.mywaycompose.domain.model.MainTask

data class OneMainTaskState(
    val isLoading:Boolean = false,
    val task:MainTask? = null,
    val error:String = ""
)