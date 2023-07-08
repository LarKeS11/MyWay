package com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.state

import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.model.MainTask

data class IdeasPullState(
    val showTaskForm:Boolean = false,
    val task:String = "",
    val ideas:List<Idea> = emptyList(),
    val mainTaskId:Int? = null,
    val mainTasks:List<MainTask> = listOf(),
    val timeError:String = ""
)