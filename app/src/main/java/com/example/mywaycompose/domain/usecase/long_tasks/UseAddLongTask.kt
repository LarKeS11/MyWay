package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository

class UseAddLongTask(
    private val localLongTasksRepository: LocalLongTasksRepository,
    private val remoteLongTasksRepository: RemoteLongTasksRepository
) {

    suspend fun execute(longTask: LongTask){
        localLongTasksRepository.addLongTask(longTask)
        remoteLongTasksRepository.addLongTask(longTask)
    }

}