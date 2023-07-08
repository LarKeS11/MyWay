package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository

class UseAddShortMainTask(
    private val localMainTasksRepository: LocalMainTasksRepository
) {

    suspend fun execute(mainTask: MainTask){
        localMainTasksRepository.addMainTask(mainTask)
    }

}