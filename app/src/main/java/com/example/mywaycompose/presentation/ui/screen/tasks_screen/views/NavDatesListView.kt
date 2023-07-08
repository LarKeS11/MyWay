package com.example.mywaycompose.presentation.ui.screen.tasks_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.domain.model.toDateServer
import com.example.mywaycompose.presentation.theme.DaysItemColor
import com.example.mywaycompose.presentation.theme.SelectedDayColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.gilory
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
fun NavDatesListView(
    days:List<Pair<String, String>>,
    toSelect: (String) -> Unit,
    initialIndex:Int,
    colors:ThemeColors
) {
    val coroutineScope = rememberCoroutineScope()
    val selectedItem = mutableStateOf(initialIndex)
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = selectedItem.value)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth(),
        state = scrollState
    ){
        coroutineScope.launch {
            scrollState.scrollToItem(selectedItem.value)
        }
        itemsIndexed(days){ index, item ->
            val isSelected = index == selectedItem.value
            Text(
                text = AnnotatedString("${item.first.toDateServer().day} ${item.second}"),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = gilory,
                    fontWeight = FontWeight.Bold,
                    color = if(isSelected) colors.selected_nav_calendar_day else colors.nav_calendar_day,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            selectedItem.value = item.first.toDateServer().day.toInt() - 1
                            toSelect(item.first)
                        }
                    )
            )
        }
    }

}