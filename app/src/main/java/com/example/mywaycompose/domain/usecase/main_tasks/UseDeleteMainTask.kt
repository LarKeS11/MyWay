package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository
import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository

class UseDeleteMainTask(
    private val localMainTasksRepository: LocalMainTasksRepository,
    private val localSubtasksRepository: LocalSubtasksRepository,
    private val remoteMainTasksRepository: RemoteMainTasksRepository
) {

    suspend fun execute(mainTask: MainTask){

        remoteMainTasksRepository.deleteMainTask(mainTask)
        localMainTasksRepository.deleteMainTask(mainTask)
        localSubtasksRepository.deleteSubtasksByMainTaskId(mainTask.id!!)
    }

}