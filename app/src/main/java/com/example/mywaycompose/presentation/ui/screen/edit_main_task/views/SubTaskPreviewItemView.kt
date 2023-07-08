package com.example.mywaycompose.presentation.ui.screen.edit_main_task.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.domain.model.toColor
import com.example.mywaycompose.presentation.theme.TaskStrokeColor
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun SubTaskPreviewItemView(
    title:String,
    taskColor:Color,
    color:String,
    deleteCallback:() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding(top = 1.dp)) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color.toColor())
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text(
                    fontSize = 15.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Medium,
                    color = taskColor,
                    text = title,
                    textAlign = TextAlign.Center
                )
            }
        }

        IconButton(
            onClick = {
                deleteCallback()
            },
            modifier = Modifier.size(25.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                modifier = Modifier.size(25.dp),
                tint = Color.Red
            )
        }
    }
}