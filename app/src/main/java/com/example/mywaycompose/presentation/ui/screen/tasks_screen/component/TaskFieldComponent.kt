package com.example.mywaycompose.presentation.ui.screen.tasks_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.HintTextFieldColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.UsuallyTaskColor
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun TaskFieldComponent(taskState:MutableState<String>,colors:ThemeColors, submit:()->Unit, toFocus:Boolean) {

    val focusRequester = remember { FocusRequester() }


    BasicTextField(
        value = taskState.value,
        onValueChange = {
            taskState.value = it
        },
        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
        decorationBox = {it ->
            Box(Modifier.fillMaxWidth()) {
                if(taskState.value.isEmpty()){
                    Text(
                        text = "Write your task",
                        style = TextStyle(
                            color = colors.title_color,
                            fontWeight = FontWeight.Medium,
                            fontFamily = monsterrat,
                            fontSize = 14.sp
                        )
                    )
                }
            }
            it()
        },
        textStyle = TextStyle(
            color = colors.title_color,
            fontWeight = FontWeight.Medium,
            fontFamily = monsterrat,
            fontSize = 14.sp
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                submit()
            }
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)

    )
    if (toFocus){
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}