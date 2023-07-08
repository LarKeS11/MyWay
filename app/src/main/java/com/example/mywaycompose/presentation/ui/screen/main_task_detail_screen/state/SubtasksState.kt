package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.state

import com.example.mywaycompose.domain.model.SubTask

data class SubtasksState(
    val isLoading:Boolean = false,
    val tasks:List<SubTask> = emptyList(),
    val error: String = ""
)