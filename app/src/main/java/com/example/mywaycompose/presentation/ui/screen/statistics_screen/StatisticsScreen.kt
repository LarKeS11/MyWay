package com.example.mywaycompose.presentation.ui.screen.statistics_screen

import ToggleButtonsView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.TasksTitleColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.component.MyWayTopAppBar
import com.example.mywaycompose.presentation.ui.component.*
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.component.*
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.views.CompareStatisticsView
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.views.EffectiveView
import com.example.mywaycompose.utils.Constants.FirstStatistics
import com.example.mywaycompose.utils.Constants.SecondStatistics




    @Composable
fun StatisticsScreen(
    navController: NavController,
    viewModel: StatisticsViewModel = hiltViewModel(),
    colors: ThemeColors
) {

        val yourselfTasksState = viewModel.yourselfTasksStatisticState
        val usuallyTasksState = viewModel.usuallyTasksStatisticState
        val compareStatisticState = viewModel.compareTasksStatistic
        val effectiveValue = viewModel.effectiveValueState
        val effectiveStatState = viewModel.effectiveStatisticsState




        val user = viewModel._authSession.currentUser


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.main_background)
        ){

            item {
                MyWayTopAppBar(
                    title = "Statistics",
                    image = user!!.photoUrl!!,
                    showCalendarIcon = false,
                    showPlusIcon = false,
                    colors = colors,
                    profileIconCallback = {
                        navController.navigate(Screen.SwitchAppThemeScreen.route)
                    }
                )
            }
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)) {
                    Box(Modifier.padding(start = 16.dp)) {
                        Text(
                            text = "Own effective:",
                            fontSize = 18.sp,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.Bold,
                            color = colors.title_color,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp)
                            .padding(top = 20.dp)
                    ) {
                        EffectiveView(effectiveValue.value, colors = colors)
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                    if(effectiveStatState.value.isLoading){
                        LoadingView()
                    }else{
                        if(effectiveStatState.value.data.isNotEmpty()){
                            EffectiveStatistics(data = effectiveStatState.value.data, colors = colors)
                        }else{
                            NoStatisticView(colors)
                        }
                    }
                    Box(
                        Modifier.padding(start = 16.dp, top = 10.dp)
                    ) {
                        Text(
                            text = "Goals way:",
                            fontSize = 18.sp,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.Bold,
                            color = colors.title_color,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal
                        )
                    }
                    Box(Modifier.padding(start = 23.dp, end = 19.dp, top = 20.dp)) {
                        ToggleButtonsView(FirstStatistics,colors)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                        Row(Modifier.fillMaxWidth()) {
                            if(yourselfTasksState.value.tasksStatistic.isNotEmpty()) BarChartView(yourselfTasksState.value.tasksStatistic,colors, FirstStatistics)
                            if(yourselfTasksState.value.tasksStatistic.isEmpty() && !yourselfTasksState.value.isLoading) NoStatisticView(colors)
                            if(yourselfTasksState.value.isLoading) {
                                LoadingView()
                            }
                        }
                }
            }
            item{
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp)) {
                    Box(Modifier.padding(start = 16.dp)) {
                        Text(
                            text = "Tasks way:",
                            fontSize = 18.sp,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.Bold,
                            color = colors.title_color,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal
                        )
                    }
                    Box(Modifier.padding(start = 23.dp, end = 19.dp, top = 20.dp)) {
                        ToggleButtonsView(SecondStatistics,colors)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(Modifier.fillMaxWidth()) {
                        if(usuallyTasksState.value.tasksStatistic.isNotEmpty()) BarChartView(usuallyTasksState.value.tasksStatistic,colors, SecondStatistics)

                        if(usuallyTasksState.value.tasksStatistic.isEmpty() && !usuallyTasksState.value.isLoading){
                            NoStatisticView(colors)
                        }
                        if(usuallyTasksState.value.isLoading) {
                            LoadingView()
                        }
                    }
                }
            }
            item{
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp)
                    .onFocusEvent {
                        if (compareStatisticState.value.tasksStatistic == null
                            && yourselfTasksState.value.tasksStatistic.isNotEmpty()
                            && usuallyTasksState.value.tasksStatistic.isNotEmpty()
                        ) viewModel.getCompareStatistic()
                    }
                ) {
                    Box(Modifier.padding(start = 16.dp)) {
                        Text(
                            text = "Compare:",
                            fontSize = 18.sp,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.Bold,
                            color = colors.title_color,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal
                        )
                    }
                    Box(Modifier.padding(start = 23.dp, end = 19.dp, top = 20.dp)) {
                        ToggleButtonsView(SecondStatistics,colors)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(end = 20.dp, start = 5.dp)
                    ) {
                        if(compareStatisticState.value.tasksStatistic != null) CompareStatisticsView(compareStatisticState.value.tasksStatistic!!,colors)
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }

        }

}