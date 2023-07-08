package com.example.mywaycompose.presentation.ui.screen.auth_screen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.FormStrokeColor
import com.example.mywaycompose.presentation.theme.OpacityColor
import com.example.mywaycompose.presentation.theme.monsterrat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordFieldAuthComponent(state:MutableState<TextFieldValue>){

    val passwordVisible = remember {
        mutableStateOf(false)
    }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()

    OutlinedTextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        label = { Text(text = "Enter pass",  color = FormStrokeColor, fontFamily = monsterrat, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center ) },
        placeholder = { Text(text = "pass") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible.value)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible.value) "Hide" else "Show"

            IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                Icon(imageVector  = image, description)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
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
            focusedIndicatorColor =FormStrokeColor,
            focusedLabelColor = FormStrokeColor,
            placeholderColor = FormStrokeColor
        ),
        shape = RoundedCornerShape(10.dp)
    )
}