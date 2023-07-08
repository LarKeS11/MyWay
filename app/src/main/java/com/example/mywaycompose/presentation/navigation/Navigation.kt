package com.example.mywaycompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.ui.screen.auth_screen.AuthorizationScreen
import com.example.mywaycompose.presentation.ui.screen.auth_screen.SplashScreen
import com.example.mywaycompose.presentation.ui.screen.auth_screen.WelcomeScreen
import com.example.mywaycompose.presentation.ui.screen.edit_main_task.EditMainTaskScreen
import com.example.mywaycompose.presentation.ui.screen.edit_profile.SwitchAppThemeScreen
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.EditTaskScreen
import com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.IdeasPullScreen
import com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen.ListMainTasksScreen
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.MainTaskDetailScreen
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.StatisticsScreen
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.TasksScreen
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun Navigate(
    navController: NavHostController,
    colors: MutableStateFlow<ThemeColors>,
    switchAppTheme:(String) -> Unit
){


    val appColors by colors.collectAsState()


    NavHost(navController = navController, startDestination =  Screen.SplashScreen.route){
        composable(route = Screen.SplashScreen.route){
            SplashScreen(
                navController = navController,
                colors = appColors
            )
        }
        composable(route = Screen.AuthScreen.route){
            AuthorizationScreen(navController = navController)
        }
        composable(
            route = Screen.TasksScreen.route + "/{date}",
            arguments = listOf(
                navArgument("date"){
                    type = NavType.StringType
                    defaultValue = "none"
                }
            )
        ){entry ->
            val date = entry.arguments?.getString("date")
            TasksScreen(
                navController = navController,
                date = if(date!!.length == 1) null else date,
                colors = appColors
            )
        }

        composable(route = Screen.WelcomeScreen.route){
            WelcomeScreen(navController = navController)
        }
        composable(
            route = Screen.MainTasksListScreen.route
        ){
            ListMainTasksScreen(
                navController = navController,
                colors = appColors
            )
        }
        composable(
            route = Screen.EditMainTaskScreen.route + "/{id}" + "/{editable}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "none"
                },
                navArgument("editable"){
                    type = NavType.StringType
                    defaultValue = "false"
                }
            )
        ){entry ->
            val potantialId = entry.arguments!!.getString("id")!!
            EditMainTaskScreen(
                id = if(potantialId.isDigitsOnly()) potantialId.toInt() else null,
                editable = entry.arguments!!.getString("editable")!!.toBoolean() ,
                navController = navController,
                colors = appColors
            )
        }

        composable(route = Screen.StatisticsScreen.route){
            StatisticsScreen(
                navController = navController,
                colors = appColors
            )
        }
        composable(
            route = Screen.EditTaskScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "-1"
                    nullable = false
                })
        ){entry ->
            EditTaskScreen(
                navController = navController,
                id = entry.arguments?.getString("id")!!.toInt(),
                colors = appColors
            )
        }

        composable(
            route = Screen.SwitchAppThemeScreen.route
        ){
            SwitchAppThemeScreen(navController = navController, colors = appColors) {theme ->
                switchAppTheme(theme)
            }
        }


        composable(
            route = Screen.DetailMainTaskScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "-1"
                    nullable = false
                }
            )
        ){entry ->
            MainTaskDetailScreen(
                navController = navController,
                id = entry.arguments?.getString("id")!!.toInt(),
                colors = appColors
            )
        }

        composable(
            route = Screen.IdeasPullScreen.route
        ){
            IdeasPullScreen(
                navController = navController,
                colors = appColors
            )
        }



    }

}



