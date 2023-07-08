package com.example.mywaycompose.domain.repository.statistics

import com.example.mywaycompose.domain.model.TaskStatistic

interface LocalStatisticsRepository {

    suspend fun addTaskToStatistic(taskStatistic: TaskStatistic)
    suspend fun getTasksStatisticByDate(date:String):List<TaskStatistic>
    suspend fun getAllTasksStatistic():List<TaskStatistic>
    suspend fun getStatisticsByMainTaskId(mainTaskId:Int):List<TaskStatistic>

}