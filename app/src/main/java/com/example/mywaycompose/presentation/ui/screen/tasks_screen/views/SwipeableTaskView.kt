package com.example.mywaycompose.presentation.ui.screen.tasks_screen.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.OpacityColor
import com.example.mywaycompose.presentation.theme.SelectedDayColor


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableTaskView(
    deleteCallback:() -> Unit,
    toCompleteCallback:() -> Unit,
    block:Boolean = false,
    content:@Composable () -> Unit
) {

    val dismissState = rememberDismissState(
        confirmStateChange = {
            if(!block) {
                if (it == DismissValue.DismissedToStart) {
                    deleteCallback()
                }
                if (it == DismissValue.DismissedToEnd) {
                    toCompleteCallback()
                }
            }
            !block

        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(1.5f) },
        background = {
            val direction =  dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                targetValue = when(direction){
                    DismissDirection.EndToStart -> Color.Red
                    DismissDirection.StartToEnd -> SelectedDayColor
                }
            )
            val icon = when(direction){
                DismissDirection.EndToStart -> Icons.Default.Delete
                DismissDirection.StartToEnd -> Icons.Default.Done
            }
            val scale by animateFloatAsState(targetValue = if(dismissState.targetValue == DismissValue.Default) 1.0f else 1.2f)

            val alignment = when(direction){
                DismissDirection.EndToStart -> Alignment.CenterEnd
                DismissDirection.StartToEnd -> Alignment.CenterStart
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .background(color),
          //      .padding(start = 12.dp, end = 12.dp),
                contentAlignment = alignment
            ) {
                Icon(icon, contentDescription = "", modifier = Modifier.scale(scale))
            }

        },
        dismissContent = {
            Box(
                Modifier.background(OpacityColor)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = OpacityColor,
                    elevation = 0.dp
                ) {
                    content()
                }
            }
        }
    )

}