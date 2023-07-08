package com.example.mywaycompose.domain.usecase.tasks

import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository

class UseDeleteTask(
    private val localTasksRepository: LocalTasksRepository,
    private val remoteTasksRepository: RemoteTasksRepository
) {

    suspend fun execute(task:Task){
        localTasksRepository.deleteTask(task)
        remoteTasksRepository.deleteTask(task)
    }

}