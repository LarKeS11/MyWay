package com.example.mywaycompose.domain.usecase.tasks

import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository

class UseCheckSameTasks(
   private val localTasksRepository: LocalTasksRepository
    ) {

    suspend fun execute(time:String, date:String):Boolean{
        return localTasksRepository.isThereTheSame(time,date)
    }

}