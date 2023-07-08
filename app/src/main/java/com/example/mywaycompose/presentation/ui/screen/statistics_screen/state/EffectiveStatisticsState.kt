package com.example.mywaycompose.presentation.ui.screen.statistics_screen.state

data class EffectiveStatisticsState(
    val data:List<Pair<String, Int>> = listOf(),
    val isLoading:Boolean = false,
    val error:String = ""
)