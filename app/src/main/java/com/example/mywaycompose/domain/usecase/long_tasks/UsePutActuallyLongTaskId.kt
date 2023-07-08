package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository


class UsePutActuallyLongTaskId(
    private val localServiceRepository: LocalServiceRepository,
    private val remoteServiceRepository: RemoteServiceRepository
)  {

    fun execute(id:Int, init:Boolean = false){
        if(!init) remoteServiceRepository.putActuallyLongTaskId(id)
        localServiceRepository.putActuallyLongTaskId(id)
    }

}