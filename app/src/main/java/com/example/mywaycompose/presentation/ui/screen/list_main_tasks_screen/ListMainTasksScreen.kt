package com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen.views.ClickableMainTaskView
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.ui.component.MyWayTopAppBar
import com.example.mywaycompose.presentation.ui.component.AnErrorView


@Composable
fun ListMainTasksScreen(
    navController: NavController,
    viewModel: MainTaskListViewModel = hiltViewModel(),
    colors: ThemeColors
) {



    val hasBeenSelected by viewModel.hasBeenSelected.collectAsState()

    val viewModelValue = viewModel.mainTasksState.value
    val user = viewModel._authSession.currentUser

    LaunchedEffect(hasBeenSelected){
        if(hasBeenSelected != null) navController.navigate(Screen.DetailMainTaskScreen.withArgs(hasBeenSelected.toString()))
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colors.main_background)
        .padding(horizontal = 16.dp)

    ) {
        MyWayTopAppBar(
            title = "Goals",
            image = user!!.photoUrl!!,
            showCalendarIcon = false,
            showPlusIcon = true,
            plusCallback = {
                navController.navigate(Screen.EditMainTaskScreen.withArgs(" ", "false"))
            },
            colors = colors,
            profileIconCallback = {
                navController.navigate(Screen.SwitchAppThemeScreen.route)
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        if(viewModelValue.isLoading){
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        if(viewModelValue.success.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 110.dp)
            ){
                itemsIndexed(viewModelValue.success){index, item ->
                    if(!item.doubts) ClickableMainTaskView(mainTask = item, colors = colors){
                        viewModel.movingToMainTaskDetail(it)
                    }
                }
            }
        }
        if(viewModelValue.error.isNotEmpty()){
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                AnErrorView(error = viewModelValue.error, size = 14)
            }
        }
    }
}