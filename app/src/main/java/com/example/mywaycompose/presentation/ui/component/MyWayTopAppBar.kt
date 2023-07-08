package com.example.mywaycompose.presentation.ui.component

import android.net.Uri
import android.printservice.CustomPrinterIconCallback
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.theme.IconColor
import com.example.mywaycompose.presentation.theme.TasksTitleColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyWayTopAppBar(
    image:Uri,
    title:String = "",
    showCalendarIcon:Boolean = false,
    showPlusIcon:Boolean = false,
    colors:ThemeColors,
    profileIconCallback: () -> Unit,
    calendarCallback:() -> Unit = {},
    plusCallback:() -> Unit = {}
) {
    Column(modifier = Modifier.padding(top = 20.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Card(
                    modifier = Modifier.size(30.dp),
                    shape = RoundedCornerShape(360.dp),
                    onClick = {
                        profileIconCallback()
                    }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(image),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Bold,
                    color = colors.title_color,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal
                )
            }
            Row() {
                if(showCalendarIcon) {
                    IconButton(
                        onClick = { calendarCallback() },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painterResource(id = R.drawable.calendar_icon),
                            tint = colors.icons,
                            modifier = Modifier.size(30.dp),
                            contentDescription = ""
                        )
                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                if(showPlusIcon) {
                    IconButton(onClick = {
                        plusCallback()
                    }, modifier = Modifier.size(30.dp)) {
                        Icon(
                            painterResource(id = R.drawable.plus_icon_cirlce),
                            tint = colors.icons,
                            modifier = Modifier.size(30.dp),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }

}