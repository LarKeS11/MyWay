package com.example.mywaycompose.domain.usecase.main_tasks

import android.net.Uri
import com.example.mywaycompose.data.repository.service.DataRemoteServiceRepository
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository

class UseUpdateMainTask(
    private val localMainTasksRepository: LocalMainTasksRepository,
    private val localServiceRepository: LocalServiceRepository,
    private val remoteServiceRepository: RemoteServiceRepository,
    private val remoteMainTasksRepository: RemoteMainTasksRepository
) {

    suspend fun execute(mainTask: MainTask, oldMainTask:MainTask){

        if(mainTask.imageSrc != oldMainTask.imageSrc){
            val uri = Uri.parse(mainTask.imageSrc)
            localServiceRepository.saveImage(uri,mainTask.id!!)
            val fileImage = localServiceRepository.getImageFileById(mainTask.id!!)
            mainTask.imageSrc = fileImage.absolutePath
            val image = localServiceRepository.convertFileToByteArray(fileImage)
            remoteServiceRepository.deletePhotoById(mainTask.id!!)
            remoteMainTasksRepository.pushMainTaskImageFirebase(
                image = image,
                id = mainTask.id!!
            )
        }

        localMainTasksRepository.updateMainTask(mainTask)
        remoteMainTasksRepository.pushMainTaskToDatabase(mainTask)
    }

}