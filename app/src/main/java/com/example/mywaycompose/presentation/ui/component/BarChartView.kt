package com.example.mywaycompose.presentation.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.theme.StatisticsKindFirstColor
import com.example.mywaycompose.presentation.theme.StatisticsKindSecondColor
import com.example.mywaycompose.presentation.theme.StatisticsValueTextColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.gilory
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.component.XValueTextComponent
import com.example.mywaycompose.utils.Constants.FirstStatistics
import com.example.mywaycompose.utils.Constants.SecondStatistics
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun BarChartView(
    data:List<List<Pair<String, Float>>>,
    colors:ThemeColors,
    kind:Int
) {
    val color = when (kind){
        FirstStatistics -> colors.first_statistics_color
        SecondStatistics -> colors.second_statistics
        else -> MainThemeColor
    }



    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(264.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ) {
        item { 
            Spacer(modifier = Modifier.width(10.dp))
        }
        items(data){ res ->
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .width(290.dp)
                        .height(220.dp)
                ) {

                    items(res){

                        val size = remember {
                            Animatable(0f)
                        }

                        LaunchedEffect(size) {
                            launch {
                                delay(300)
                                size.animateTo(it.second, animationSpec = tween(300, easing = FastOutSlowInEasing))
                            }
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxHeight(size.value)
                                .width(30.dp),
                            backgroundColor = color,
                            shape = RoundedCornerShape(5.dp)
                        ) {

                        }



                    }

                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    res.forEachIndexed{index, item ->
                        Box(
                            Modifier.width(30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.first,
                                fontFamily = gilory,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 14.sp,
                                color = colors.title_color
                            )
                        }
                    }
                }
            }
        }
    }

}
