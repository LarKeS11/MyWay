package com.example.mywaycompose.presentation.ui.screen.auth_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mywaycompose.R



@Composable
fun AuthBackgroundComponent() {
    Box(Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.welcome_dude),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF06010A), Color(0x8B3F3F)),
                    startY = 1000f,
                    endY = 0f
                )
            ).rotate(270f)
        ) {

        }
    }
}