package com.example.mywaycompose.domain.usecase.subtasks

import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository

class UseDeleteTasksDependsOnSubtask(
    private val tasksLocalRepository: LocalTasksRepository,
    private val remoteTasksRepository: RemoteTasksRepository
) {

    suspend fun execute(id:Int){

        tasksLocalRepository.getAllTasks().forEach {
            if(it.idSubTask !=null && it.idSubTask == id){
                tasksLocalRepository.deleteTask(it)
                remoteTasksRepository.deleteTask(it)
            }
        }

    }

}