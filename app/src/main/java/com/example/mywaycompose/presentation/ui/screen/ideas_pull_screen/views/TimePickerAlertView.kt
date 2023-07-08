package com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.theme.StrongButtonColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.TitleMyWayColor
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.component.AnErrorView

@Composable
fun TimePickerAlertView(
    colors:ThemeColors,
    error:String = "",
    onSubmit:(Int, Int) -> Unit,
    onDismiss:() -> Unit
) {
    var pickerValue by remember { mutableStateOf(0) }
    var pickerValue1 by remember { mutableStateOf(0) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            backgroundColor = colors.main_background,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Select time:",
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.sp,
                    color = colors.title_color,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NumberPicker(
                        value = pickerValue,
                        dividersColor = colors.title_color,
                        textStyle = TextStyle(color = colors.title_color),
                        range = 0..23,
                        onValueChange = {
                            pickerValue = it
                        }
                    )
                    Column {
                        Divider(modifier = Modifier
                            .size(4.dp)
                            .clip(RoundedCornerShape(100))
                            .background(colors.title_color))
                        Spacer(modifier = Modifier.height(2.dp))
                        Divider(modifier = Modifier
                            .size(4.dp)
                            .clip(RoundedCornerShape(100))
                            .background(colors.title_color))
                    }
                    NumberPicker(
                        value = pickerValue1,
                        dividersColor = colors.title_color,
                        textStyle = TextStyle(color = colors.title_color),
                        range = 0..59,
                        onValueChange = {
                            pickerValue1 = it
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                
                if(error.isNotEmpty()){
                    AnErrorView(error = error, size = 12)
                    Spacer(modifier = Modifier.height(14.dp))
                }
                
                Button(
                    onClick = {
                        onSubmit(pickerValue, pickerValue1)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colors.unselectedTabsBar)
                ) {
                    Text(
                        text = "Done!",
                        fontFamily = monsterrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = colors.title_color,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                if(error.isNotEmpty()){
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }

        }
    }
}