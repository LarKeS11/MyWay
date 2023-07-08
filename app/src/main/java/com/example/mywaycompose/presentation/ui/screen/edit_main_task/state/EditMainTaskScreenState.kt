package com.example.mywaycompose.presentation.ui.screen.edit_main_task.state

import android.net.Uri
import com.example.mywaycompose.domain.model.MainTask

data class EditMainTaskScreenState(
    val textMainTaskFormValue:String = "",
    val iconMainTaskFormValue:String = "",
    val activeColorPicker:Boolean = false,
    val selectedSubtaskFormColor:String? = null,
    val selectedSubtaskFormTitle:String = "",
    val subtaskError:String = "",
    val selectedMainTaskImage:Uri? = null,
    val mainTaskError:String = ""
)