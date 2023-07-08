package com.example.mywaycompose.presentation.navigation

 sealed class Screen(val route:String) {
     object AuthScreen: Screen("auth")
     object SplashScreen: Screen("splash")
     object RegScreen: Screen("reg")
     object TasksScreen:Screen("tasks")
     object WelcomeScreen:Screen("welcome")
     object MainTasksListScreen:Screen("main_tasks_list")
     object AddMainTaskScreen:Screen("add_main_task")
     object DetailMainTaskScreen:Screen("detail_main_task")
     object EditTaskScreen:Screen("edit_task")
     object StatisticsScreen:Screen("statistics_screen")
     object IdeasPullScreen:Screen("ideas_pull_screen")
     object TasksV2Screen:Screen("tasks_v2_screen")
     object EditMainTaskScreen:Screen("edit_main_task_screen")
     object SwitchAppThemeScreen:Screen("switch_app_theme_screen")

     fun withArgs(vararg args: String):String{
         return buildString {
             append(route)
             args.forEach { arg ->
                 append("/$arg")
             }
         }
     }

}