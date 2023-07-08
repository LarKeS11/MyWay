package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.model.toColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun SubtasksPreviewListView(tasks:List<SubTask>, colors: ThemeColors) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.Center
    ){
        tasks.forEach{item ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.padding(top = 4.dp)) {
                    Card(
                        modifier = Modifier.size(16.dp),
                        shape = RoundedCornerShape(100),
                        backgroundColor = item.color.toColor()
                    ){}
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = item.title,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = colors.title_color
                )
            }
        }
    }

}