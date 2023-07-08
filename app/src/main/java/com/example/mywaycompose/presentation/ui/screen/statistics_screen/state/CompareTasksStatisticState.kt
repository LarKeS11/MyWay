package com.example.mywaycompose.presentation.ui.screen.statistics_screen.state

data class CompareTasksStatisticState(
    val tasksStatistic:Pair<List<Pair<String, Float>>,List<Pair<String, Float>>>? = null,
    val isLoading:Boolean = false,
    val error:String = ""
)