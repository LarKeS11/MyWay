package com.example.mywaycompose.domain.repository.tasks

import com.example.mywaycompose.domain.model.Task

interface LocalTasksRepository {

    suspend fun addTask(task: Task)
    suspend fun getTasks(date:String):List<Task>
    suspend fun getTaskById(id:Int):Task
    suspend fun updateTask(task: Task)
    suspend fun isThereTheSame(time:String, date:String):Boolean
    suspend fun deleteTask(task:Task)
    suspend fun getAllTasks():List<Task>
    suspend fun cleanTasksDatabase()

    suspend fun deleteAllTables()
}