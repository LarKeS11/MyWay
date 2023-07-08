package com.example.mywaycompose.presentation.navigation




import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mywaycompose.presentation.theme.*
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun BottomNavBar(
    items:List<BottomNavItem>,
    navController: NavController,
    colors: MutableStateFlow<ThemeColors>,
    onClick: (BottomNavItem) -> Unit
) {

    val appColors by colors.collectAsState()

    val backStackEntry = navController.currentBackStackEntryAsState()

    val showBottomNavState = remember {
        mutableStateOf(true)
    }

    if(backStackEntry.value?.destination?.route != null){
        val backStackEntryValue = backStackEntry.value?.destination?.route!!.split("/")[0]

        showBottomNavState.value = !((backStackEntryValue == Screen.AddMainTaskScreen.route)
                || (backStackEntryValue == Screen.DetailMainTaskScreen.route)
                || (backStackEntryValue == Screen.EditTaskScreen.route)
                || (backStackEntryValue == Screen.AuthScreen.route)
                || (backStackEntryValue == Screen.SplashScreen.route)
                || (backStackEntryValue == Screen.EditMainTaskScreen.route)
                || (backStackEntryValue == Screen.SwitchAppThemeScreen.route)
                )
    }

    if(showBottomNavState.value){
        Box(Modifier.height(75.dp)) {

            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(appColors.main_background, appColors.main_background.copy(alpha = 0.4f)),
                            startY = 100f,
                            endY = 50f
                        )
                    )
                    .align(Alignment.BottomEnd)
                    .rotate(270f)) {}

            BottomNavigation(
                backgroundColor = OpacityColor,
                elevation = 0.dp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 20.dp)

            ) {
                items.forEach { item ->
                    if(backStackEntry.value?.destination?.route != null) {
                        val backStack =
                            backStackEntry.value?.destination?.route!!.split("/")[0]
                        val item1 = item.route.split("/")[0]
                        val selected = item1 == backStack
                        BottomNavigationItem(
                            selected = selected,
                            onClick = { onClick(item) },
                            selectedContentColor = appColors.selected_menu_item,
                            unselectedContentColor = appColors.un_selected_menu_item,
                            icon = {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(painterResource(id = item.icon), contentDescription = "")
                                    if (selected) {
                                        Text(
                                            text = item.name,
                                            fontSize = 10.sp,
                                            color = appColors.title_color,
                                            fontFamily = monsterrat,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }




}




