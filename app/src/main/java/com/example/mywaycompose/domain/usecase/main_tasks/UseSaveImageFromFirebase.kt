package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository

class UseSaveImageFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val localServiceRepository: LocalServiceRepository
) {
    fun execute(id:Int){
        val ref = remoteServiceRepository.getImageReference(id)

        val limit: Long = 1024 * 1024 * 10

        ref.getBytes(limit).addOnSuccessListener {
            localServiceRepository.saveByteArrayAsFile(id, it)
        }

    }
}