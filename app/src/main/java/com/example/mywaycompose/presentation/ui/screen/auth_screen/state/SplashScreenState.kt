package com.example.mywaycompose.presentation.ui.screen.auth_screen.state

data class SplashScreenState(
    val tasksHasBeenLoaded:Boolean = false,
    val mainTasksHasBeenLoaded:Boolean = false,
    val subtasksHasBeenLoaded:Boolean = false,
    val ideasHasBeenLoaded:Boolean = false,
    val tasksStatisticsHasBeenLoaded:Boolean = false,
    val actuallyMainTaskIdHasBeenLoaded:Boolean = false,
    val actuallySubtaskIdHasBeenLoaded:Boolean = false,
    val actuallyTasksStatisticsIdHasBeenLoaded:Boolean = false,
    val actuallyLongTasksIdHasBeenLoaded:Boolean = false,
    val longTasksHasBeenLoaded:Boolean = false,
    val longTasksStatHasBeenLoaded:Boolean = false

)