package com.example.mywaycompose.presentation.ui.screen.statistics_screen.state

import com.example.mywaycompose.domain.model.TaskStatistic
import java.time.LocalDate

data class TasksStatisticsState(
   val tasksStatistic:List<List<Pair<String, Float>>> = emptyList(),
   val isLoading:Boolean = false,
   val error:String = ""
)