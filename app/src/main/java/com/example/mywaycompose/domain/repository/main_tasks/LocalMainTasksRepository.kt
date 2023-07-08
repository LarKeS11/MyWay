package com.example.mywaycompose.domain.repository.main_tasks

import com.example.mywaycompose.domain.model.MainTask

interface LocalMainTasksRepository {
    suspend fun addMainTask(mainTask:MainTask)
    suspend fun getAllMainTasks():List<MainTask>
    suspend fun getMainTaskById(id:Int):MainTask
    suspend fun getMainTaskBytTitle(title:String):MainTask
    suspend fun deleteMainTask(task:MainTask)
    suspend fun updateMainTask(task:MainTask)
    suspend fun deleteAllMainTasks()
}