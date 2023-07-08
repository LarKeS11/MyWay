package com.example.mywaycompose.presentation.ui.screen.edit_task_screen.component

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.mywaycompose.R
import com.example.mywaycompose.data.repository.service.toLocalDate
import com.example.mywaycompose.data.repository.service.toDateString
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.component.MyWayTitleView
import java.time.LocalDate
import java.util.*

@SuppressLint("ResourceAsColor")
@Composable
fun AlertRangeDatePickerComponent(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (String,String, Boolean) -> Unit,
    currentDate:String,
    colors:ThemeColors,
    compare:(LocalDate) -> Boolean
) {
    var first_date = currentDate
    var second_date = currentDate


    Dialog(onDismissRequest = onDismiss) {
        Card(
            backgroundColor = colors.main_background,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(colors.main_background)
                    .padding(horizontal = 20.dp, vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Set period:",
                    fontSize = 15.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Bold,
                    color = colors.title_color,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal
                )
                AndroidView(
                    modifier = Modifier.fillMaxWidth(),
                    factory = { context ->
                        val view = LayoutInflater.from(context).inflate(R.layout.date_picker, null)
                        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
                        val calendar = Calendar.getInstance() // show today by default
                        datePicker.init(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ) { _, year, monthOfYear, dayOfMonth ->
                            val date = Calendar.getInstance().apply {
                                set(year, monthOfYear, dayOfMonth)
                            }.time
                            first_date = date.toLocalDate().toDateString()
                        }
                        datePicker
                    }
                )
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDownward,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = colors.title_color
                    )
                }
                AndroidView(
                    modifier = Modifier.fillMaxWidth(),
                    factory = { context ->
                        val view = LayoutInflater.from(context).inflate(R.layout.date_picker, null)
                        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
                        val calendar = Calendar.getInstance() // show today by default
                        datePicker.init(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ) { _, year, monthOfYear, dayOfMonth ->
                            val date = Calendar.getInstance().apply {
                                set(year, monthOfYear, dayOfMonth)
                            }.time
                            second_date = date.toLocalDate().toDateString()
                        }
                        datePicker
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(onClick = onNegativeClick) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {

                        val compared = compare(first_date.toLocalDate())

                        onPositiveClick(
                            first_date,
                            second_date,
                            first_date != second_date && compared
                        )
                    }) {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }

}

