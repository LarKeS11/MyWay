package com.example.mywaycompose.domain.usecase.statistics

import com.example.mywaycompose.data.repository.statistics.DataLocalStatisticsRepository
import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.statistics.LocalStatisticsRepository

class UseAddTasksStatisticsFromFirebaseToLocalDatabase(
    private val localStatisticsRepository: LocalStatisticsRepository
) {

    suspend fun execute(taskStatistic: TaskStatistic){
        localStatisticsRepository.addTaskToStatistic(taskStatistic)
    }

}