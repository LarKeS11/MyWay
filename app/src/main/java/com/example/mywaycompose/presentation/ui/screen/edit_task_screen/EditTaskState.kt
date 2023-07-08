package com.example.mywaycompose.presentation.ui.screen.edit_task_screen


data class EditTaskState(
    val idMainTask:Int? = null,
    val idSubtask:Int? = null,
    val showRangePicker:Boolean = false,
    val firstRangeDate:String? = null,
    val secondRangeDate:String? = null,
    val taskGrade:Int = 0,
    val activeDatePicker:Boolean = false,
    val error:String = ""
)