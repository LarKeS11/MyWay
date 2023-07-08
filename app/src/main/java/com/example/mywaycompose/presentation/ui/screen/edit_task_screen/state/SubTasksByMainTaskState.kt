package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.state

import com.example.mywaycompose.domain.model.SubTask

data class SubTasksByMainTaskState(
    val subtasks:List<SubTask> = emptyList(),
    val isLoading:Boolean = false,
    val error:String = ""
)