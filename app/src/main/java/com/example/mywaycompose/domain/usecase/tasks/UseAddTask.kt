package com.example.mywaycompose.domain.usecase.tasks

import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import javax.inject.Inject

class UseAddTask @Inject constructor(private val localTasksRepository: LocalTasksRepository) {
    suspend fun execute(task:Task){
        localTasksRepository.addTask(task)
    }
}