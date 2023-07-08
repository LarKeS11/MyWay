package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.model.toColor
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.theme.OpacityColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun SubTasksListView(
    tasks:List<SubTask>,
    colors:ThemeColors,
    callback:(Int) -> Unit
) {
    val selectedSubtask = remember {
        mutableStateOf(-1)
    }


    Column(
        modifier = Modifier.padding(start = 35.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        tasks.forEachIndexed{index,item ->
            Row(
            ) {
                Box(Modifier.padding(top = 4.dp)) {
                    Button(
                        onClick = {
                            if(selectedSubtask.value != index) selectedSubtask.value = index
                            else {
                                selectedSubtask.value = -1
                                callback(-1)
                                return@Button
                            }
                            callback(tasks[index].id!!)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = if(selectedSubtask.value == index) item.color.toColor() else OpacityColor),
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = item.color.toColor(),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .size(20.dp),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.elevation(0.dp)
                    ) {
                        if(selectedSubtask.value == index) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "",
                                    tint = MainThemeColor,
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = item.title,
                    fontSize = 18.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Medium,
                    color = colors.title_color
                )
            }
        }
    }
}