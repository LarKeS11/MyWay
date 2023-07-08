package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.TaskStrokeColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimeTaskFieldView(
    hint:String,
    text:TextFieldValue,
    modifier: Modifier = Modifier,
    colors: ThemeColors,
    setText:(TextFieldValue) -> Unit
) {

    val scope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    FormWrapper(colors = colors) {
        BasicTextField(
            value = text,
            onValueChange = {
                val time = it.text
                if(time.length >= 6) return@BasicTextField
                if(time.length == 3 && time[time.length-1] != ':'){
                    setText(
                        TextFieldValue(text = "${time[0]}${time[1]}:${time[2]}", selection = TextRange(it.text.length + 1))
                    )
                    return@BasicTextField
                }

                setText(it)

            },
            cursorBrush = SolidColor(colors.form_text_color),
            modifier = modifier
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusEvent {
                    if (it.isFocused) {
                        scope.launch {
                            delay(200)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }
                .padding(top = 7.dp)
            ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(
                color = colors.form_text_color,
                fontWeight = FontWeight.Medium,
                fontFamily = monsterrat,
                fontSize = 14.sp
            ),
            singleLine = true,
            decorationBox = {
                if(text.text.isEmpty()){
                    Text(
                        text = hint,
                        color = colors.form_text_color,
                        fontWeight = FontWeight.Medium,
                        fontFamily = monsterrat,
                        fontSize = 14.sp
                    )
                }
                it()
            }
        )
    }
}