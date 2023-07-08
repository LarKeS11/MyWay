package com.example.mywaycompose.presentation.ui.screen.tasks_screen.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import com.example.mywaycompose.presentation.theme.HintTextFieldColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun TasksTimeFieldComponent(
    timeState:MutableState<TextFieldValue>,
    toSubmitTime:()->Unit,
    toFocus:Boolean,
    colors:ThemeColors
) {

    val focusRequester = remember { FocusRequester() }

    val oneHourFlag = remember {
        mutableStateOf(false)
    }

    BasicTextField(
        value = timeState.value,
        onValueChange = {
            try {
                val letter = it.text

                if (letter.isNotEmpty()) {
                    val checkNum = letter.filter { !it.isWhitespace() }.toInt()
                }

                if (letter.length > 6) return@BasicTextField
                if (letter.length > 1) {
                    if ((letter.length < timeState.value.text.length) && (letter[letter.length - 1].toString() == " ") && (timeState.value.text[timeState.value.text.length - 1].toString() == " ")) {
                        val letterWithoutSpace = letter.filter { res -> !res.isWhitespace() }
                        if (letterWithoutSpace.length == 1) {
                            oneHourFlag.value = false
                        }
                        timeState.value = TextFieldValue(
                            text = if (letterWithoutSpace.length == 1) "" else letterWithoutSpace[0].toString(),
                            selection = TextRange(1)
                        )
                        return@BasicTextField
                    }
                }

                if (timeState.value.text.isEmpty() && it.text[0].toString().toInt() > 2) {
                    oneHourFlag.value = true
                }

                if ((letter.length == 2) && (it.text[0].toString()
                        .toInt() == 2 && it.text[1].toString().toInt() > 3)
                ) {
                    oneHourFlag.value = true
                    timeState.value = TextFieldValue(
                        text = it.text[0] + "   " + it.text[1],
                        selection = TextRange(letter.length + 3)
                    )
                    return@BasicTextField
                }

                if (it.text.isEmpty()) {
                    oneHourFlag.value = false
                }

                if (letter.length == 2 && oneHourFlag.value) {
                    timeState.value = TextFieldValue(
                        text = it.text[0] + "   " + it.text[1],
                        selection = TextRange(letter.length + 3)
                    )
                    return@BasicTextField
                }

                if (letter.length == 2 && !oneHourFlag.value) {
                    timeState.value = TextFieldValue(
                        text = it.text + "  ",
                        selection = TextRange(letter.length + 2)
                    )
                    return@BasicTextField
                }

                timeState.value = it
            } catch (e: Exception) {

            }

        },
        cursorBrush = SolidColor(Color.Transparent),
        textStyle = TextStyle(
            color = colors.title_color,
            fontWeight = FontWeight.Bold,
            fontFamily = monsterrat,
            fontSize = 12.sp
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                toSubmitTime()
            }
        ),
        modifier = Modifier
            .height(17.dp)
            .width(43.dp)
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .fillMaxSize()
            ) {
                Box(Modifier.fillMaxSize()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PumpTimeTasks(timeState.value.text.isEmpty(), colors = colors)
                        PumpTimeTasks(
                            timeState.value.text.length <= 1,
                            oneHourFlag.value,
                            timeState,
                            colors = colors
                        )
                        if (timeState.value.text.length > 1 && timeState.value.text[0].toString()
                                .toInt() < 2
                        ) Spacer(modifier = Modifier.width(if (timeState.value.text[0] != '0') 3.dp else 6.dp))
                        if (timeState.value.text.length > 1 && timeState.value.text[0].toString()
                                .toInt() > 2
                        ) Spacer(modifier = Modifier.width(4.dp))
                        if (timeState.value.text.length > 1 && timeState.value.text[0].toString()
                                .toInt() == 2
                        ) Spacer(modifier = Modifier.width(4.dp))
                        DotsDecorationTasksTime(colors = colors)
                        if (timeState.value.text.length > 3 || (timeState.value.text.length > 2 && oneHourFlag.value)) Spacer(
                            modifier = Modifier.width(3.dp)
                        )
                        PumpTimeTasks(timeState.value.text.length <= 4, colors = colors)
                        PumpTimeTasks(timeState.value.text.length <= 5, colors = colors)
                    }
                }
            }
            innerTextField()
        }
    )
    if (toFocus){
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
fun PumpTimeTasks(show:Boolean, hide:Boolean = false, numState:MutableState<TextFieldValue>? = null, colors: ThemeColors) {
    if(show && !hide){
        HintTasksTime(colors)
    }else{
        if((numState != null)){
            val parsedStr = numState.value.text.filter { res -> !res.isWhitespace() }
            Log.d("fucking_str", parsedStr)
            if((parsedStr.length > 1) &&parsedStr[1].toString().toInt() == 1){
                Log.d("fucking_str", "parsedStr")
                Divider(Modifier
                    .width((if (parsedStr[0].toString() == "1") (2.5).dp else (4.5).dp))
                    .height(0.dp))
                    return
                }
            if((parsedStr.length > 1) &&parsedStr[1].toString().toInt() == 0){
                Log.d("fucking_str", "parsedStr")
                Divider(Modifier

                    .width((if (parsedStr[0].toString() == "1") (6.2).dp else (8).dp))
                    .height(0.dp))
                return
            }
        }
        Divider(if(hide) Modifier
            .width(2.dp)
            .height(0.dp) else Modifier
            .width((5.5).dp)
            .height(0.dp))
    }



}

@Composable
fun DotsDecorationTasksTime(colors:ThemeColors) {
    Column(Modifier.padding(top = 5.dp)) {
        Card(modifier = Modifier.size(3.dp), shape = RoundedCornerShape(360.dp), backgroundColor = colors.title_color) {}
        Spacer(modifier = Modifier.height(2.dp))
        Card(modifier = Modifier.size(3.dp), shape = RoundedCornerShape(360.dp), backgroundColor = colors.title_color) {}
    }
}

@Composable
fun HintTasksTime(colors:ThemeColors) {
    Row() {
        Box(Modifier.padding(top = 5.dp)) {
            Divider(
                Modifier
                    .width(9.dp)
                    .height(1.dp)
                    .background(colors.title_color)
            )
        }
        Spacer(modifier = Modifier.width((0.5).dp))
    }
}
