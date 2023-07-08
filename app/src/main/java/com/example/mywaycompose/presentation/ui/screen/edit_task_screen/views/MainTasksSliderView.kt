package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.rememberImagePainter
import com.example.mywaycompose.R
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.component.MyWayTitleView
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.component.SelectedMainTaskTitleComponent
import com.google.accompanist.pager.*
import java.io.File
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainTasksSliderView(
    tasks:List<MainTask>,
    cardState:PagerState,
    colors: ThemeColors,
    selectedId:Int
) {



    HorizontalPager(
        count = tasks.size,
        contentPadding = PaddingValues(horizontal = 42.dp),
        state = cardState
    ){page ->
        Card(
           modifier = Modifier
               .graphicsLayer {
                   val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                   lerp(
                       start = 0.85f,
                       stop = 1f,
                       fraction = 1f - pageOffset.coerceIn(0f, 1f)
                   ).also { scale ->
                       scaleX = scale
                       scaleY = scale
                   }

                   // We animate the alpha, between 50% and 100%
                   alpha = lerp(
                       start = 0.5f,
                       stop = 1f,
                       fraction = 1f - pageOffset.coerceIn(0f, 1f)
                   )
               }
               .fillMaxWidth()

            ,
            backgroundColor = MainThemeColor,
            shape = RoundedCornerShape(10.dp),
            elevation = 0.dp
        ) {
            if(page == 0){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    border = BorderStroke(width = 1.5.dp, Color(0x99D6E5E5)),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = colors.unselectedTabsBar
                ) {

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(verticalArrangement = Arrangement.spacedBy(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Without goal",
                                color = colors.title_color,
                                fontSize = 18.sp,
                                fontFamily = monsterrat,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.high_nav_icon),
                                contentDescription = "",
                                tint = colors.title_color,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }

                }
            }
            else{
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .offset {
                        val pageOffset =
                            this@HorizontalPager.calculateCurrentOffsetForPage(page)
                        IntOffset(
                            x = (36.dp * pageOffset).roundToPx(),
                            y = 0
                        )
                    }

                ) {
                    Box() {
                        Image(
                            painter = rememberImagePainter (data = File(tasks[page].imageSrc)),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(vertical = 10.dp, horizontal = 20.dp)) {
                            Row(modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0x991E1E1E))
                                .padding(5.dp)

                            ) {
                                if(tasks[page].id!! == selectedId) {
                                    Text(
                                        text = tasks[page].title.ifEmpty { "Read books" },
                                        color = colors.high_light_color,
                                        fontFamily = monsterrat,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                else{
                                    Text(
                                        text = tasks[page].title.ifEmpty { "Read books" },
                                        fontSize = 14.sp,
                                        fontFamily = monsterrat,
                                        fontWeight = FontWeight.Bold,
                                        color = colors.espacially_first_task_title,
                                        textAlign = TextAlign.Center,
                                        fontStyle = FontStyle.Normal
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = tasks[page].icon.ifEmpty { "\uD83C\uDF59" },
                                    color = colors.high_light_color,
                                    fontFamily = monsterrat,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                    }
                }
            }


        }
    }
}