package com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen.views

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
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.theme.OpacityColor
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.theme.IconBackgroundColor
import com.example.mywaycompose.presentation.theme.MainTaskCardColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat
import java.io.File


@Composable
fun MainTaskCardView(
    task:String,
    image: String?,
    icon:String,
    colors:ThemeColors
) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor =  colors.main_background,
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = image ?: R.drawable.test_main_task_img,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(47.dp)
//                    .align(Alignment.TopCenter)
//                    .background(colors.unselectedTabsBar.copy(alpha = 0.45f))
//            ) {
//
//            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 23.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(modifier = Modifier
                    .height(97.dp)
                    .fillMaxWidth()
                    .rotate(180f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(colors.unselectedTabsBar, Color(0x1A2D2937)),
                            startY = 0f,
                            endY = 400f
                        )
                    )) {

                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .align(Alignment.BottomCenter)
                    .background(colors.unselectedTabsBar)
            ) {

            }
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp, horizontal = 20.dp)) {
                Box(Modifier.align(Alignment.TopEnd)) {
                    Card(
                        backgroundColor = IconBackgroundColor,
                        shape = RoundedCornerShape(7.dp),
                        modifier = Modifier.size(26.dp)
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = icon.ifEmpty { "\uD83C\uDF59" })
                        }
                    }
                }
                Box(modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0x991E1E1E))
                    .padding(5.dp)

                ) {
                    Text(
                        text = task.ifEmpty { "Read books" },
                        fontSize = 14.sp,
                        fontFamily = monsterrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Normal
                    )
                }
            }

        }
    }

    
}