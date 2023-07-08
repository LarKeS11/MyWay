package com.example.mywaycompose.data.repository.main_tasks

import com.example.mywaycompose.data.local.database.entity.MainTaskEntity
import com.example.mywaycompose.data.local.database.service.DatabaseService
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository

class DataLocalMainTasksRepository(
    private val databaseService: DatabaseService
): LocalMainTasksRepository {
    override suspend fun addMainTask(mainTask: MainTask) {
        databaseService.addMainTask(mainTask.toMainTaskEntity())
    }

    override suspend fun getAllMainTasks(): List<MainTask> {
        return databaseService.getAllMainTasks().map { it.toMainTask() }
    }

    override suspend fun getMainTaskById(id:Int): MainTask {
        return databaseService.getMainTaskById(id).toMainTask()
    }

    override suspend fun getMainTaskBytTitle(title: String):MainTask {
        return databaseService.getMainTaskByTitle(title).toMainTask()
    }

    override suspend fun deleteMainTask(task: MainTask) {
        databaseService.deleteMainTask(task.toMainTaskEntity())
    }

    override suspend fun updateMainTask(task: MainTask) {
        databaseService.updateMainTask(task.toMainTaskEntity())
    }

    override suspend fun deleteAllMainTasks() {
        databaseService.deleteAllMainTasks()
    }
}

fun MainTask.toMainTaskEntity(): MainTaskEntity {
    return MainTaskEntity(
        id = id,
        title = title,
        icon = icon,
        imageSrc = imageSrc,
        doubts = this.doubts,
        idIdea = idIdea
    )
}

fun MainTaskEntity.toMainTask():MainTask{
    return MainTask(
        id = id,
        title = title,
        icon = icon,
        imageSrc = imageSrc,
        doubts = this.doubts,
        idIdea = idIdea
    )
}