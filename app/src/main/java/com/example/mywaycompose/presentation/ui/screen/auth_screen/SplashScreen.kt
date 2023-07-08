package com.example.mywaycompose.presentation.ui.screen.auth_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.AuthTitleColor
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.screen.auth_screen.component.AuthTitleComponent

@Composable
fun SplashScreen(
    navController: NavController,
    colors: ThemeColors,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val firstLaunch = viewModel.firstLaunch


    LaunchedEffect(firstLaunch){
        if(firstLaunch) navController.navigate(Screen.AuthScreen.route)
    }

    LaunchedEffect(Unit){
        if(!firstLaunch) {
            viewModel.loadTasks()
            viewModel.loadMainTasks()
            viewModel.loadSubtasks()
            viewModel.loadIdeas()
            viewModel.loadTasksStatistics()
            viewModel.getIds()
            viewModel.loadLongTasks()
            viewModel.loadLongTasksStat()
        }
    }

    Log.d("efggfefgb","${state.tasksHasBeenLoaded} " +
            "${state.mainTasksHasBeenLoaded}" +
            " ${state.subtasksHasBeenLoaded} " +
            "${state.ideasHasBeenLoaded} " +
            "${state.tasksStatisticsHasBeenLoaded} " +
            "${state.actuallyMainTaskIdHasBeenLoaded} " +
            "${state.actuallySubtaskIdHasBeenLoaded} " +
            "${state.actuallyTasksStatisticsIdHasBeenLoaded} " +
            "${state.longTasksHasBeenLoaded}" +
            "${state.actuallyLongTasksIdHasBeenLoaded}" +
            "${state.longTasksStatHasBeenLoaded}"
    )

    LaunchedEffect(
        state
    ){
        if(
            state.tasksHasBeenLoaded
            && state.mainTasksHasBeenLoaded
            && state.subtasksHasBeenLoaded
            && state.ideasHasBeenLoaded
            && state.tasksStatisticsHasBeenLoaded
            && state.actuallyMainTaskIdHasBeenLoaded
            && state.actuallySubtaskIdHasBeenLoaded
            && state.actuallyTasksStatisticsIdHasBeenLoaded
            && state.longTasksHasBeenLoaded
            && state.actuallyLongTasksIdHasBeenLoaded
            && state.longTasksStatHasBeenLoaded
        ){
            navController.navigate(Screen.TasksScreen.withArgs(" "))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.main_background),
        verticalArrangement = Arrangement.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Wait while data is loading..",
                fontSize = 24.sp,
                fontFamily = monsterrat,
                fontWeight = FontWeight.Bold,
                color = colors.title_color,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal
            )
            CircularProgressIndicator()
        }
    }
    
}