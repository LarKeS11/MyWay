package com.example.mywaycompose.domain.usecase.main_tasks
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import java.io.File

class UsePushMainTaskImageToFirebase(
    private val remoteMainTasksRepository: RemoteMainTasksRepository,
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute(file:File, id:Int){
        val bytes = localServiceRepository.convertFileToByteArray(file)
        remoteMainTasksRepository.pushMainTaskImageFirebase(bytes, id)
    }

}