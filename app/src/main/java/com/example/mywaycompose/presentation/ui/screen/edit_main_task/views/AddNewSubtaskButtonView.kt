package com.example.mywaycompose.presentation.ui.screen.edit_main_task.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.theme.IconColor
import com.example.mywaycompose.presentation.theme.ThemeColors

@Composable
fun AddNewSubtaskButtonView(
    colors: ThemeColors,
    callback:() -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            onClick = {
                callback()
            }, modifier = Modifier
                .size(35.dp)
                .align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.square_add_button
                ),
                contentDescription = "",
                modifier = Modifier.size(35.dp),
                tint = colors.icons
            )
        }
    }
}