package com.example.mywaycompose.data.repository.tasks

import com.example.mywaycompose.data.local.database.entity.TaskEntity
import com.example.mywaycompose.data.local.database.service.DatabaseService
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository

class DataLocalTasksRepository(
    val databaseService: DatabaseService
): LocalTasksRepository {
    override suspend fun addTask(task: Task) {
        databaseService.addTask(task.toTaskEntity())
    }

    override suspend fun getTasks(date:String): List<Task> {
        return databaseService.getTasks(date).map { it.toTask() }
    }

    override suspend fun getTaskById(id: Int): Task {
        return databaseService.getTaskById(id).toTask()
    }

    override suspend fun updateTask(task: Task) {
        databaseService.updateTask(task.toTaskEntity())
    }

    override suspend fun isThereTheSame(time: String, date: String):Boolean {
        return databaseService.isThereTheSameTask(time, date)
    }

    override suspend fun deleteTask(task: Task) {
        databaseService.deleteTask(task.toTaskEntity())
    }

    override suspend fun getAllTasks(): List<Task> {
        return databaseService.getAllTasks().map { it.toTask() }
    }

    override suspend fun cleanTasksDatabase() {
        databaseService.cleanTasksDatabase()
    }

    override suspend fun deleteAllTables() {
        databaseService.deleteAllTables()
    }
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id  = id,
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

fun TaskEntity.toTask():Task{

    return Task(
        id  = id,
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