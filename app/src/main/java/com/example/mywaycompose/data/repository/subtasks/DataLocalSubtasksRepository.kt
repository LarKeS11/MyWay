package com.example.mywaycompose.data.repository.subtasks

import android.util.Log
import com.example.mywaycompose.data.local.database.entity.SubTaskEntity
import com.example.mywaycompose.data.local.database.service.DatabaseService
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository

class DataLocalSubtasksRepository(
    val databaseService: DatabaseService
): LocalSubtasksRepository {
    override suspend fun addSubtask(subTask: SubTask) {
        databaseService.addSubtask(subTask.toSubTaskEntity())
    }

    override suspend fun getSubtasksByMainTaskId(id: Int): List<SubTask> {
        return databaseService.getSubtasksByMainTaskId(id).map { it.toSubTask() }
    }

    override suspend fun getSubtaskById(id: Int): SubTask {
        val subtask = databaseService.getSubtaskById(id)
        return subtask.toSubTask()
    }

    override suspend fun deleteSubtasksByMainTaskId(id: Int) {
        databaseService.deleteSubtasksByMainTaskId(id)
    }

}

fun SubTask.toSubTaskEntity(): SubTaskEntity {
    Log.d("vcxdfvcdfvc","${id} ${title} ${color} ${mainTaskId}")

    return SubTaskEntity(
        id = id!!,
        mainTaskId = mainTaskId,
        title = title,
        color = color
    )
}

fun SubTaskEntity.toSubTask():SubTask{
    return SubTask(
        id = id,
        mainTaskId = mainTaskId,
        title = title,
        color = color
    )
}