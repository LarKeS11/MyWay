package com.example.mywaycompose.domain.usecase.tasks

import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository

class UsePushTaskToFirebase(
    private val remoteTasksRepository: RemoteTasksRepository
) {

    fun execute(task:Task){
        remoteTasksRepository.pushTaskToDatabase(task)
    }

}