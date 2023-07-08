package com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.component.TaskFieldView
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor

@Composable
fun AddSubtaskAlertFormView(
    subtask:String,
    onSubmit:(String, String) -> Unit,
    onDismiss:() -> Unit,
    colors: ThemeColors
) {

    val subtaskState = remember {
        mutableStateOf(subtask)
    }
    var currentColor by remember {
        mutableStateOf(HsvColor.from(StrongButtonColor))
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            backgroundColor = colors.main_background,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Check your subtask:",
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.sp,
                    color = colors.title_color,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row(Modifier.fillMaxWidth()) {
                    Box(Modifier.widthIn(max = 225.dp)) {
                        TaskFieldView(
                            hint = "Read book",
                            text = subtaskState.value,
                            modifier = Modifier.fillMaxWidth().height(35.dp).padding(7.dp),
                            colors = colors
                        ){
                            subtaskState.value = it
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        modifier = Modifier
                            .size(35.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = currentColor.toColor()),
                        onClick = {}
                    ){

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ClassicColorPicker(
                    color = currentColor,
                    modifier = Modifier
                        .height(300.dp),
                    onColorChanged = { hsvColor: HsvColor ->
                        currentColor = hsvColor
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        val color = android.graphics.Color.HSVToColor(
                            floatArrayOf(currentColor.hue, currentColor.saturation, currentColor.value )
                        )

                        onSubmit("${color.red};${color.blue};${color.green};${color.alpha}", subtaskState.value)
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
            }
        }
    }

}