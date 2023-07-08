package com.example.mywaycompose.data.repository.statistics

import com.example.mywaycompose.data.local.database.entity.TaskStatisticEntity
import com.example.mywaycompose.data.local.database.service.DatabaseService
import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.statistics.LocalStatisticsRepository

class DataLocalStatisticsRepository(
    private val databaseService: DatabaseService
): LocalStatisticsRepository {
    override suspend fun addTaskToStatistic(taskStatistic: TaskStatistic) {
        databaseService.addTaskToStatistic(taskStatistic.toTaskStatisticEntity())
    }

    override suspend fun getTasksStatisticByDate(date:String): List<TaskStatistic> {
        return databaseService.getTasksStatisticByDate(date = date).map { it.toTaskStatistic() }
    }

    override suspend fun getAllTasksStatistic(): List<TaskStatistic> {
        return databaseService.getAllTasksStatistic().map { it.toTaskStatistic() }
    }

    override suspend fun getStatisticsByMainTaskId(mainTaskId: Int): List<TaskStatistic> {
        return databaseService.getTasksStatisticByMainTaskId(mainTaskId).map { it.toTaskStatistic() }
    }
}

fun TaskStatistic.toTaskStatisticEntity(): TaskStatisticEntity {
    return TaskStatisticEntity(
        id = id,
        date = date,
        priority = priority,
        mainTaskId = mainTaskId
    )
}

fun TaskStatisticEntity.toTaskStatistic():TaskStatistic{
    return TaskStatistic(
        id = id,
        date = date,
        priority = priority,
        mainTaskId = mainTaskId
    )
}