package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository

class UsePushLongTaskToFirebase(
    private val remoteLongTasksRepository: RemoteLongTasksRepository
) {

    fun execute(longTask: LongTask){
        remoteLongTasksRepository.addLongTask(longTask)
    }

}