package com.example.mywaycompose.domain.usecase.statistics

import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository
import kotlinx.coroutines.flow.Flow

class UseGetEffectiveStatisticsByDayFromFirebase(
    private val remoteStatisticsRepository: RemoteStatisticsRepository
) {

    fun execute(date:String):Flow<Int>{
        return remoteStatisticsRepository.getEffectiveStatisticsByDay(date)
    }

}