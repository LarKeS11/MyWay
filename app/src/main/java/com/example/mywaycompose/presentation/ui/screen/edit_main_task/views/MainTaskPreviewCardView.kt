package com.example.mywaycompose.presentation.ui.screen.edit_main_task.views

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.mywaycompose.presentation.theme.MainTaskCardColor
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.theme.ThemeColors

@Composable
fun MainTaskPreviewCardView(
    image:Uri?,
    colors: ThemeColors,
    title:String,
    icon:String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = colors.main_background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                if(image == null || image.toString().isEmpty()) R.drawable.main_task_preview else image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(47.dp)
                    .align(Alignment.TopCenter)
                    .background(colors.main_background.copy(alpha = 0.55f))
            ) {

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 23.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .rotate(180f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(colors.main_background, Color(0x1A2D2937)),
                            startY = 0f,
                            endY = 200f
                        )
                    )) {

                }
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 20.dp)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.title_color,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(26.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .background(colors.main_background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = icon,
                        fontSize = 14.sp,
                        fontFamily = monsterrat,
                        fontWeight = FontWeight.SemiBold,
                        color = MainTaskCardColor
                    )
                }
            }

        }
    }

}