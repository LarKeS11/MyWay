package com.example.mywaycompose.presentation.ui.screen.auth_screen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextFieldAuthComponent(label:String, placeholder:String, state:MutableState<TextFieldValue>) {
    val scope = rememberCoroutineScope()

    val bringIntoViewRequester = remember { BringIntoViewRequester() }



    OutlinedTextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        label = {
            Text(
                text = label,
                fontFamily = monsterrat,
                fontWeight = FontWeight.Medium,
                color = FormStrokeColor,
                modifier = Modifier
            ) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .fillMaxWidth()
            .padding()
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
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = OpacityColor,
            cursorColor = FormStrokeColor,
            unfocusedIndicatorColor = FormStrokeColor,
            focusedIndicatorColor = FormStrokeColor,
            focusedLabelColor = FormStrokeColor,
            textColor = FormStrokeColor,
            placeholderColor = FormStrokeColor
        ),
        shape = RoundedCornerShape(10.dp)

    )

}