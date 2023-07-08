package com.example.mywaycompose.presentation.ui.screen.auth_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.AuthOptionalTitleColor
import com.example.mywaycompose.presentation.theme.HighLightAuthColor
import com.example.mywaycompose.presentation.theme.monsterrat

@Composable
fun BottomOptionalComponent(navigateTo:() -> Unit) {
    Row() {
        Text(
            text = "Already have account?",
            fontSize = 14.sp,
            fontFamily = monsterrat,
            fontWeight = FontWeight.Medium,
            color = AuthOptionalTitleColor,
            fontStyle = FontStyle.Normal
        )
        Spacer(modifier = Modifier.width(6.dp))
        ClickableText(
            text = AnnotatedString("Sign in"),
            style = TextStyle(
                fontSize = 14.sp,
                color = HighLightAuthColor,
                fontFamily = monsterrat,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal
            ),
            onClick = {navigateTo()}
        )

        
    }
}