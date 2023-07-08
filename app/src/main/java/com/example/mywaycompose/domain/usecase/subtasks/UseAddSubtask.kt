package com.example.mywaycompose.domain.usecase.subtasks

import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository
import com.example.mywaycompose.domain.repository.subtasks.RemoteSubtasksRepository

class UseAddSubtask(
    private val localSubtasksRepository: LocalSubtasksRepository,
    private val remoteSubtasksRepository: RemoteSubtasksRepository
) {

    suspend fun execute(subTask: SubTask){
        try {
            remoteSubtasksRepository.pushSubtaskToDatabase(subTask)
            localSubtasksRepository.addSubtask(subTask)
        }catch (_:java.lang.Exception){

        }

    }

}