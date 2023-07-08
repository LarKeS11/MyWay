package com.example.mywaycompose.presentation.ui.screen.statistics_screen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.EffectiveBackgroundColor
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.theme.ThemeColors

@Composable
fun EffectiveView(
    points:String,
    colors: ThemeColors
) {
    Card(
        modifier =  Modifier.fillMaxWidth(),
        backgroundColor = colors.effective_block,
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 9.dp)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Your effective now:",
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = colors.title_color
                )
                Text(
                    text = points,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = colors.title_color
                )
            }
            Box(
               modifier = Modifier
                   .width(85.dp)
                   .fillMaxHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {
                if(colors.type == "day") LayoutEffectiveShow(R.drawable.effective_decoration)
                else LayoutEffectiveShow(R.drawable.effective_night)
            }
        }
    }
}

@Composable
fun LayoutEffectiveShow(resource: Int){
    Image(
        painter = painterResource(id = resource),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
