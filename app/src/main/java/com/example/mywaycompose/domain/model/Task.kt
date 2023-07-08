package com.example.mywaycompose.domain.model

import com.example.mywaycompose.data.repository.firebase_models.FirebaseTask

class Task(
    var id:Int? = null,
    val task:String,
    var time:String,
    val date:String,
    var status:Boolean,
    val idBigTask:Int? = null,
    val idSubTask:Int? = null,
    val grade:Int? = null,
    var mainTaskImage:String? = null,
    val subtaskColor:String? = null,
    val mainTaskTitle:String? = null,
    val subtaskTitle:String? = null
)

fun FirebaseTask.toTask():Task{
    return Task(
        id  = null,
        task = task!!,
        time = time!!,
        date = date!!,
        status = status!!,
        idBigTask = idBigTask,
        idSubTask = idSubTask,
        grade = grade,
        mainTaskImage = mainTaskImage,
        subtaskTitle = subtaskTitle,
        subtaskColor = subtaskColor,
        mainTaskTitle = mainTaskTitle
    )
}

fun Task.toFirebaseTask():FirebaseTask{
    return FirebaseTask(
        task = task,
        time = time,
        date = date,
        status = status,
        idBigTask = idBigTask,
        idSubTask = idSubTask,
        grade = grade,
        mainTaskImage = mainTaskImage,
        subtaskTitle = subtaskTitle,
        subtaskColor = subtaskColor,
        mainTaskTitle = mainTaskTitle
    )
}

fun LongTask.toTask():Task{
    return Task(
        task = task,
        time = startDate!! + "-" + endDate,
        date = startDate,
        status = false,
        idBigTask = idMainTask,
        idSubTask = idSubtask,
        mainTaskImage = mainTaskImage,
        subtaskTitle = subtaskTitle,
        subtaskColor = subtaskColor,
        mainTaskTitle = mainTaskTitle
    )
}