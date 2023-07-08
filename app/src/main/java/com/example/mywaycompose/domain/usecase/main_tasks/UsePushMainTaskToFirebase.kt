package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository

class UsePushMainTaskToFirebase(
    private val remoteMainTasksRepository: RemoteMainTasksRepository
) {

    fun execute(mainTask: MainTask){
        remoteMainTasksRepository.pushMainTaskToDatabase(mainTask)
    }

}