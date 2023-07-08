package com.example.mywaycompose.presentation.ui.screen.tasks_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.theme.CompleteIconColor
import com.example.mywaycompose.presentation.theme.EditIconColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.TrashIconColor
import com.example.mywaycompose.utils.Constants

@Composable
fun OptionButtonsBar(
    kind:String,
    colors:ThemeColors,
    progress:Float = 0f,
    callbackRemove:() -> Unit = {},
    callbackCheck:() -> Unit = {},
    callbackToComplete:() -> Unit = {},
    callbackToIdeas:() -> Unit = {},
) {
    if(kind == Constants.TASK_OPTION_BUTTONS) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TaskSubElementComponent(
                icon = R.drawable.ic_baseline_auto_fix_normal_24,
                title = "To ideas",
                colors = colors,
                color = EditIconColor,
                modifier = Modifier.fillMaxWidth(),
                callback = {
                    callbackToIdeas()
                })
        }
    }

    if(kind == Constants.LONG_TASK_OPTION_BUTTONS){
        Column() {
            Box(
                modifier = Modifier.padding(horizontal = 10.dp).padding(vertical = 15.dp)
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(5.dp)),
                    color = colors.first_statistics_color,
                    backgroundColor = colors.unselectedTabsBar
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 11.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TaskSubElementComponent(
                    icon = R.drawable.trash_icon,
                    title = "Remove",
                    colors = colors,
                    color = TrashIconColor,
                    callback = {
                        callbackRemove()
                    })
                TaskSubElementComponent(
                    icon = R.drawable.ic_baseline_check_circle_outline_24,
                    title = "check",
                    colors = colors,
                    color = EditIconColor,
                    callback = {
                        callbackCheck()
                    })
                TaskSubElementComponent(
                    icon = R.drawable.check_square,
                    title = "complete",
                    colors = colors,
                    color = CompleteIconColor,
                    callback = {
                        callbackToComplete()
                    })
            }
        }
    }


}