package com.example.mywaycompose.presentation.ui.screen.edit_main_task

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywaycompose.R
import com.example.mywaycompose.domain.model.toColor
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.presentation.ui.screen.edit_main_task.views.AlertColorPickerView
import com.example.mywaycompose.presentation.ui.component.AnErrorView
import com.example.mywaycompose.presentation.ui.screen.edit_main_task.views.AddNewSubtaskButtonView
import com.example.mywaycompose.presentation.ui.screen.edit_main_task.views.MainTaskPreviewCardView
import com.example.mywaycompose.presentation.ui.screen.edit_main_task.views.SubTaskPreviewItemView
import com.example.mywaycompose.presentation.ui.component.TaskFieldView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditMainTaskScreen(
    id:Int?,
    editable:Boolean,
    navController: NavController,
    colors: ThemeColors,
    viewModel: EditMainTaskViewModel = hiltViewModel()
) {


    val state by viewModel.state.collectAsState()
    val subtasksState = viewModel.subtasksState
    val hasBeenDone by viewModel.hasBeenDone

    val oldMainTask = viewModel.oldMainTask

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if(uri != null) viewModel.setMainTaskImage(uri)
        }

    LaunchedEffect(Unit){
        if(editable){
            viewModel.getMainTask(id!!)
        }
    }

    LaunchedEffect(hasBeenDone){
        if(hasBeenDone){
            navController.navigate(Screen.MainTasksListScreen.route)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.main_background)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "New goal",
                    fontSize = 20.sp,
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Bold,
                    color = colors.title_color,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal
                )
                IconButton(onClick = {
                    if(oldMainTask == null){
                        navController.popBackStack()
                        return@IconButton
                    }
                    if(oldMainTask.doubts){
                        navController.navigate(Screen.IdeasPullScreen.route)
                        return@IconButton
                    }
                    navController.popBackStack()
                }, modifier = Modifier.size(30.dp)) {
                    Icon(
                        painterResource(id = R.drawable.close_circle),
                        tint = colors.icons,
                        modifier = Modifier.size(30.dp),
                        contentDescription = ""
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
            MainTaskPreviewCardView(
                image = state.selectedMainTaskImage,
                title = state.textMainTaskFormValue.ifEmpty { "Read 1000 books" } ,
                colors = colors,
                icon = state.iconMainTaskFormValue.ifEmpty {  "\uD83C\uDF59" }
            )
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
            Divider(modifier = Modifier
                .width(160.dp)
                .height(3.dp)
                .clip(RoundedCornerShape(40))
                .background(colors.form_text_color))
            Spacer(modifier = Modifier.height(33.dp))
        }

        item {
            Button(
                onClick = {
                    galleryLauncher.launch("image/*")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                border = BorderStroke(width = 1.5.dp, colors.form_text_color),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Select an image",
                            color = colors.title_color,
                            fontSize = 16.sp,
                            fontFamily = monsterrat,
                            fontWeight = FontWeight.Normal
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.image_icon),
                            contentDescription = "",
                            tint = colors.icons,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

            }
        }
        
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Set your goal and icon:",
                fontSize = 18.sp,
                fontFamily = monsterrat,
                fontWeight = FontWeight.Bold,
                color = colors.title_color,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TaskFieldView(
                        hint = "New goal",
                        text = state.textMainTaskFormValue,
                        modifier = Modifier
                            .width(252.dp)
                            .height(35.dp)
                            .padding(7.dp),
                        colors = colors
                    ){
                        viewModel.setTextMainTaskForm(it)
                    }
                    TaskFieldView(
                        hint = "\uD83C\uDF59",
                        text = state.iconMainTaskFormValue,
                        modifier = Modifier
                            .width(25.dp)
                            .height(35.dp)
                            .padding(top = 7.dp),
                        maxLen = 2,
                        colors = colors
                    ){
                        viewModel.setIconMainTaskForm(it)
                    }
                }
            }
        }

        item{
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Divide goal by subtasks:",
                fontSize = 18.sp,
                fontFamily = monsterrat,
                fontWeight = FontWeight.Bold,
                color = colors.title_color,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        items(subtasksState){item ->
            SubTaskPreviewItemView(title = item.title,taskColor = colors.title_color, color = item.color) {
                viewModel.deleteSubtask(item)
            }
            Spacer(modifier = Modifier.height(2.dp))
        }

        item{
            if(subtasksState.isNotEmpty()) Spacer(modifier = Modifier.height(23.dp))
        }


        item {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TaskFieldView(
                        hint = "New goal",
                        text = state.selectedSubtaskFormTitle,
                        modifier = Modifier
                            .width(252.dp)
                            .height(35.dp)
                            .padding(7.dp),
                        colors = colors
                    ){
                        viewModel.changeSubtaskFormTitle(it)
                    }
                    Button(
                        modifier = Modifier.size(35.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = if(state.selectedSubtaskFormColor == null)colors.second_statistics else state.selectedSubtaskFormColor!!.toColor()),
                        elevation = ButtonDefaults.elevation(0.dp),
                        onClick = {
                            viewModel.switchActiveColorPicker(true)
                        }
                    ) {

                    }
                }
                if(state.subtaskError.isNotEmpty())AnErrorView(error = state.subtaskError, size = 14)
                AddNewSubtaskButtonView(colors){
                    viewModel.addNewSubtask()
                }
            }
        }


        item {

        }


        item {
            if(state.activeColorPicker) {
                AlertColorPickerView(
                    onDismiss = {
                        viewModel.switchActiveColorPicker(false)
                    },
                    onNegativeClick = {
                        viewModel.switchActiveColorPicker(false)
                    },
                    onPositiveClick = { red, blue, green, alpha ->
                        viewModel.setSubtaskFormColor("${red};${blue};${green};${alpha}")
                        viewModel.switchActiveColorPicker(false)
                    }
                )
            }
        }
        item {
            if(state.mainTaskError.isNotEmpty()) AnErrorView(error = state.mainTaskError, size = 14)
        }
        item {
            Spacer(modifier = Modifier.height(18.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        viewModel.doneFilling(id)
                    },
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(painterResource(
                        id = R.drawable.plus_icon_cirlce),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp),
                        tint = colors.icons
                    )
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }

    }

}