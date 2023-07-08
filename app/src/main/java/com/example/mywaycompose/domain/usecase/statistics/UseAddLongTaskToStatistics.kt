package com.example.mywaycompose.domain.usecase.statistics

import com.example.mywaycompose.domain.model.LongTaskStat
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository

class UseAddLongTaskToStatistics(
    private val localLongTasksRepository: LocalLongTasksRepository
) {

    suspend fun execute(longTaskStat: LongTaskStat){
        localLongTasksRepository.insertLongTaskStat(longTaskStat)
    }

}