package com.example.mywaycompose.domain.usecase.subtasks

import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository
import com.example.mywaycompose.domain.repository.subtasks.RemoteSubtasksRepository

class UseDeleteSubtasksByMainTaskId(
    private val localSubtasksRepository: LocalSubtasksRepository,
    private val remoteSubtasksRepository: RemoteSubtasksRepository
) {

    suspend fun execute(id:Int){
        localSubtasksRepository.deleteSubtasksByMainTaskId(id)
        remoteSubtasksRepository.cleanSubtasksByMainTaskId(id)
    }

}