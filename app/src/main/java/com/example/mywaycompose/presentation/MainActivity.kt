package com.example.mywaycompose.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.navigation.BottomNavBar
import com.example.mywaycompose.presentation.navigation.BottomNavItem
import com.example.mywaycompose.presentation.navigation.Navigate
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.mywaycomposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val accountViewModel: MainActivityViewModel by viewModels()
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mywaycomposeTheme {
                val navController = rememberNavController()

                Scaffold(
                    Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavBar(
                            items = listOf(
                                BottomNavItem(
                                    icon = R.drawable.ic_baseline_architecture_24,
                                    name = "Ideas",
                                    route = "ideas_pull_screen"
                                ),
                                BottomNavItem(
                                    icon = R.drawable.tasks_nav_icon,
                                    name = "Tasks",
                                    route = Screen.TasksScreen.withArgs(" ")
                                ),
                                BottomNavItem(
                                    icon = R.drawable.statistics_nav_icon,
                                    name = "Statistics",
                                    route = "statistics_screen"
                                ),
                                BottomNavItem(
                                    icon = R.drawable.high_nav_icon,
                                    name = "Goals",
                                    route = "main_tasks_list"
                                )
                            ),
                            navController = navController,
                            colors = accountViewModel.currentMainThemeColors
                        ){
                            navController.navigate(it.route)
                        }
                    }
                ){
                    Navigate(
                        navController = navController,
                        colors = accountViewModel.currentMainThemeColors
                    ){theme ->
                        accountViewModel.switchMainThemeColors(theme)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    mywaycomposeTheme {
        Greeting("Android")
    }
}