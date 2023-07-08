package com.example.mywaycompose.presentation.ui.screen.tasks_screen.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LongTaskOptionsButton(
    callback:()->Unit,
    resource: Int,
    color: Color
) {
    IconButton(
        onClick = {
            callback()
        },
        modifier = Modifier.size(24.dp)
    ) {
        Icon(
            painter = painterResource(id = resource),
            contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = color
        )
    }
}

