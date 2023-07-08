package com.example.mywaycompose.presentation.ui.screen.tasks_screen.state

data class LongTaskStatState(
    val isLoading:Boolean = false,
    val stat:List<Pair<String, Boolean>> = emptyList()
)