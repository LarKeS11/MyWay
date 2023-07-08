package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.state

import com.example.mywaycompose.domain.model.MainTask

data class MainTaskDetailState(
    val isLoading:Boolean = false,
    val task:MainTask? = null,
    val error:String = ""
)