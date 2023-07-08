package com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Task
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.presentation.theme.*
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IdeaCardView(
    idea:Idea,
    colors: ThemeColors,
    onMainTaskClick:(Idea) -> Unit,
    onDeleteIdea:(Idea) -> Unit,
    onSubtask:(Idea) -> Unit,
    onCalendar:() -> Unit
) {

    RevealSwipe(
        modifier = Modifier.padding(vertical = 5.dp),
        directions = setOf(
            RevealDirection.StartToEnd,
            RevealDirection.EndToStart
        ),
        backgroundCardStartColor = StatisticsKindSecondColor,
        maxRevealDp = 140.dp,
        hiddenContentStart = {
            Row(
            ) {
                IconButton(onClick = {
                    onMainTaskClick(idea)
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    onSubtask(idea)
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Task,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    onCalendar()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        hiddenContentEnd = {
            IconButton(onClick = {
                onDeleteIdea(idea)
            }) {
                Icon(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null
                )
            }

        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 65.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            backgroundColor = colors.task_background_color,

            ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 15.dp)) {
                Text(
                    text = idea.idea,
                    color = colors.title_color,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = monsterrat,
                    textAlign = TextAlign.Center
                )
            }

        }
    }


}