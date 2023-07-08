package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseDeleteAllMainTasks(
    private val localMainTasksRepository: LocalMainTasksRepository,
    private val localServiceRepository: LocalServiceRepository
) {

    suspend fun execute(){
        val tasks = localMainTasksRepository.getAllMainTasks()
        tasks.forEach {
            val file = localServiceRepository.getImageFileById(it.id!!)
            localServiceRepository.deleteFile(file)
        }
        localMainTasksRepository.deleteAllMainTasks()
    }

}