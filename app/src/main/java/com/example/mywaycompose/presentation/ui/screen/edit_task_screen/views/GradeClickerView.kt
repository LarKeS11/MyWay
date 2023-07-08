package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.StrongButtonColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun GradeClickerView(
    callback:(Int) -> Unit,
    number:Int,
    colors:ThemeColors,
    numChange:(String) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                if(number > 0) callback(-1)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colors.grade_btn)
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "",
                tint = Color.White
            )
        }
        BasicTextField(
            value = number.toString(),
            onValueChange = {
                if(it.isEmpty()) {
                    numChange("0")
                    return@BasicTextField
                }
                if("-" in it) return@BasicTextField
                if(it.toIntOrNull() == null) return@BasicTextField
                if(it[0] == '0') return@BasicTextField
                if(it.toInt() > 100) return@BasicTextField
                if(it.toInt() < 0) return@BasicTextField
                if(number == 0) {
                    numChange(it[0].toString())
                    return@BasicTextField
                }
                numChange(it)
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontFamily = monsterrat,
                fontWeight = FontWeight.SemiBold,
                color = colors.espacially_second_task_title,
                textAlign = TextAlign.Center
            ),
            cursorBrush = SolidColor(colors.espacially_second_task_title),
            modifier = Modifier
                .height(30.dp)
                .width(40.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = {
                it()
            }
        )
        Button(
            onClick = {
                if(number < 100) callback(1)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colors.grade_btn)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint =  Color.White
            )
        }
    }

}