package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.theme.OpacityColor


@Composable
fun BackArrowButtonComponent(callback:() -> Unit) {

    Button(
        modifier = Modifier.size(65.dp),
        shape = RoundedCornerShape(360.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = OpacityColor),
        onClick = {callback()},
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(id =R.drawable.back_arrow_with_background),
            contentDescription = "",
            modifier = Modifier.size(65.dp)
        )
    }

}