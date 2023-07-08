package com.example.mywaycompose.domain.usecase.statistics

import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository

class UsePushTaskToFirebaseStatistics(
    private val remoteStatisticsRepository: RemoteStatisticsRepository
) {

     fun execute(taskStatistic: TaskStatistic){
        remoteStatisticsRepository.pushTaskStatistic(taskStatistic)

    }

}