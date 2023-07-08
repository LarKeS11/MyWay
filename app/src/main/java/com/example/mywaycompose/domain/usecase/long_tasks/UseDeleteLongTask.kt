package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository

class UseDeleteLongTask(
    private val localLongTasksRepository: LocalLongTasksRepository,
    private val remoteLongTasksRepository: RemoteLongTasksRepository
) {

    suspend fun execute(longTask: LongTask){
        localLongTasksRepository.deleteLongTask(longTask)
        remoteLongTasksRepository.deleteLongTask(longTask)
    }

}