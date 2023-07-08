package com.example.mywaycompose.presentation.ui.screen.auth_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.ArrowBackColor
import com.example.mywaycompose.presentation.theme.HalfAuthButtonOpacity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackerAuthComponent(navigateTo:() -> Unit) {
    Card(
        modifier = Modifier
            .height(36.dp)
            .width(36.dp),
        backgroundColor = HalfAuthButtonOpacity,
        shape = RoundedCornerShape(360.dp),
        onClick = {
            navigateTo()
        }
    ) {
        Box(Modifier.fillMaxWidth().padding(top = 9.dp, bottom = 9.dp, start = 12.dp, end = 5.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "",
                tint = ArrowBackColor
            )
        }
    }
}