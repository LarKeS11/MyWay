package com.example.mywaycompose.presentation.ui.screen.auth_screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.ui.screen.auth_screen.component.AuthBackgroundComponent
import com.example.mywaycompose.presentation.ui.screen.auth_screen.component.AuthButtonComponent
import com.example.mywaycompose.presentation.ui.screen.auth_screen.component.AuthTitleComponent
import com.example.mywaycompose.presentation.ui.screen.auth_screen.component.BottomOptionalComponent

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        AuthBackgroundComponent()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 65.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            AuthTitleComponent(title = "Welcome to mywaycompose!")
            Spacer(modifier = Modifier.height(40.dp))
            AuthButtonComponent("Start"){
                navController.navigate(Screen.RegScreen.route)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BottomOptionalComponent(){
                    navController.navigate(Screen.AuthScreen.route)
                }
            }
        }
    }
}