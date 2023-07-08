package com.example.mywaycompose.presentation.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.TaskStrokeColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.views.FormWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskFieldView(
    modifier: Modifier = Modifier,
    hint:String,
    text:String,
    colors: ThemeColors,
    textHintTextStyle:TextStyle = TextStyle(
        color = colors.form_text_color,
        fontWeight = FontWeight.Medium,
        fontFamily = monsterrat,
        fontSize = 14.sp
    ),
    visibleStroke:Boolean = true,
    maxLen:Int? = null,
    onDoneListener:() -> Unit = {},
    callback:(String) -> Unit
) {

    val scope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    FormWrapper(colors = colors, visibleStroke = visibleStroke) {
        BasicTextField(
            value = text,
            onValueChange = {
                if(maxLen != null && it.length > maxLen) return@BasicTextField
                callback(it)
            },
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
            ,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDoneListener()
                }
            ),
            cursorBrush = SolidColor(colors.form_text_color),
            textStyle = TextStyle(
                color = colors.form_text_color,
                fontWeight = FontWeight.Medium,
                fontFamily = monsterrat,
                fontSize = 14.sp
            ),
            singleLine = true,
            decorationBox = {
                if(text.isEmpty()){
                    Text(
                        text = hint,
                        style = textHintTextStyle
                    )
                }
                it()
            }
        )
    }

}