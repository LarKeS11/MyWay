package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository

class UseGetMainTasksImageRef(
    private val remoteServiceRepository: RemoteServiceRepository
) {

    fun execute(id:Int){

        remoteServiceRepository.getImageReference(id)

    }

}