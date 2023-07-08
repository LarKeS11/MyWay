package com.example.mywaycompose.domain.usecase.tasks

import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository

class UseToCompleteTask(
    private val localTasksRepository: LocalTasksRepository,
    private val remoteTasksRepository: RemoteTasksRepository
) {

    suspend fun execute(task:Task){
        task.status = true
        remoteTasksRepository.pushTaskToDatabase(task)
        localTasksRepository.updateTask(task)
    }

}
