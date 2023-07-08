package com.example.mywaycompose.data.repository.long_tasks

import android.util.Log
import com.example.mywaycompose.data.local.database.entity.LongTaskEntity
import com.example.mywaycompose.data.local.database.entity.LongTaskStatEntity
import com.example.mywaycompose.data.local.database.service.DatabaseService
import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.LongTaskStat
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import javax.inject.Inject

class DataLocalLongTasksRepository @Inject constructor(
    private val databaseService: DatabaseService
): LocalLongTasksRepository {

    override suspend fun insertLongTaskStat(longTaskStat: LongTaskStat) {
        Log.d("long_task_info",longTaskStat.date)
        databaseService.insertLongTaskStat(longTaskStat.toLongTaskEntity())
    }

    override suspend fun getLongTaskStatById(id: Int): List<LongTaskStat> {
       return databaseService.getLongTaskStatById(id).map { it.toLongTaskStat() }
    }

    override suspend fun addLongTask(longTask: LongTask) {
        databaseService.addLongTask(longTask.toLongTaskEntity())
    }

    override suspend fun getLongTasks(): List<LongTask> {
        return databaseService.getLongTasks().map { it.toLongTask() }
    }

    override suspend fun deleteLongTask(longTask: LongTask) {
        databaseService.deleteLongTask(longTask.toLongTaskEntity())
    }

    override suspend fun findSameStat(id: Int, date: String): Boolean {
        return databaseService.findSameLongTaskStat(id, date)
    }


}

fun LongTaskStat.toLongTaskEntity(): LongTaskStatEntity {
    return LongTaskStatEntity(
        id = id,
        idTask = idTask,
        date = date
    )
}

fun LongTaskStatEntity.toLongTaskStat():LongTaskStat{
    return LongTaskStat(
        id = id,
        idTask = idTask,
        date = date
    )
}

fun LongTask.toLongTaskEntity():LongTaskEntity{
    return LongTaskEntity(
        id = id,
        task = task,
        idMainTask = idMainTask,
        idSubtask = idSubtask,
        startDate = startDate,
        endDate = endDate,
        mainTaskImage = mainTaskImage,
        subtaskTitle = subtaskTitle,
        subtaskColor = subtaskColor,
        mainTaskTitle = mainTaskTitle
    )
}

fun LongTaskEntity.toLongTask():LongTask{
    return LongTask(
        id = id,
        task = task,
        idMainTask = idMainTask,
        idSubtask = idSubtask,
        startDate = startDate,
        endDate = endDate,
        mainTaskImage = mainTaskImage,
        subtaskTitle = subtaskTitle,
        subtaskColor = subtaskColor,
        mainTaskTitle = mainTaskTitle
    )
}