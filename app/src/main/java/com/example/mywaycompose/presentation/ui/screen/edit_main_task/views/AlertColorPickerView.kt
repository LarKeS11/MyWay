package com.example.mywaycompose.presentation.ui.screen.edit_main_task.views

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColor
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.ui.component.MyWayTitleView
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.toColorInt


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlertColorPickerView(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (Int, Int, Int, Int) -> Unit
) {

    var currentColor by remember {
        mutableStateOf(HsvColor.from(Color.Red))
    }
    
    Dialog(onDismissRequest = onDismiss) {

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = MainThemeColor
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 27.dp)
                    .padding(bottom = 8.dp)

            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    MyWayTitleView(title = "Select color:", size = 15)
                }

                Spacer(modifier = Modifier.height(15.dp))
                
                ClassicColorPicker(
                    color = currentColor,
                    modifier = Modifier
                        .height(300.dp)
                        .padding(16.dp),
                    onColorChanged = { hsvColor: HsvColor ->
                        currentColor = hsvColor
                    }
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(onClick = onNegativeClick) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        val color = android.graphics.Color.HSVToColor(
                            floatArrayOf(currentColor.hue, currentColor.saturation, currentColor.value )
                        )



                        onPositiveClick(
                            android.graphics.Color.red(color),
                            android.graphics.Color.blue(color),
                            android.graphics.Color.green(color),
                            android.graphics.Color.alpha(color)
                        )
                    }) {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}