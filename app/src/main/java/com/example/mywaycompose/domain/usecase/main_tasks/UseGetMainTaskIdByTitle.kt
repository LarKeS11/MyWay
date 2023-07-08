package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository

class UseGetMainTaskIdByTitle(
    private val localMainTasksRepository: LocalMainTasksRepository
) {

    suspend fun execute(title:String):Int{
        return localMainTasksRepository.getMainTaskBytTitle(title).id!!
    }

}