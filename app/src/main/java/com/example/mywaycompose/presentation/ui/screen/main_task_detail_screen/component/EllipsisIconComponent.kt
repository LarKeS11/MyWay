package com.example.mywaycompose.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.SmallOpacity
import com.example.mywaycompose.presentation.theme.TitleMyWayColor

@Composable
fun EllipsisIconComponent(
    callback:() -> Unit
) {
    IconButton(
        onClick = {
            callback()
        },
        modifier = Modifier.clip(RoundedCornerShape(100)).size(38.dp).background(SmallOpacity),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            OneDot(TitleMyWayColor)
            OneDot(TitleMyWayColor)
            OneDot(TitleMyWayColor)
        }
    }
}

@Composable
fun OneDot(color: Color) {
    Divider(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .background(color)
            .size(4.dp)
    )
}