package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository

class UseDeleteLongTaskFromFirebase(
    private val remoteLongTasksRepository: RemoteLongTasksRepository
) {

    fun execute(longTask: LongTask){
        remoteLongTasksRepository.deleteLongTask(longTask)
    }

}