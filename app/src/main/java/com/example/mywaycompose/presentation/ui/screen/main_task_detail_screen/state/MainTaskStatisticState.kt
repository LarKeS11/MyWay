package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.state

data class MainTaskStatisticState(
    val isLoading:Boolean = false,
    val stat:List<ArrayList<Pair<String, Float>>> = emptyList(),
    val error:String = ""
)