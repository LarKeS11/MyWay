package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository

class UseClearDatabase(
    private val localTasksRepository: LocalTasksRepository
) {

    suspend fun execute(){
        localTasksRepository.deleteAllTables()
    }

}