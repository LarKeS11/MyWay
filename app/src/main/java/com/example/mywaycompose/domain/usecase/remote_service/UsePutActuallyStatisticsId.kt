package com.example.mywaycompose.domain.usecase.remote_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository

class UsePutActuallyStatisticsId(
    private val localServiceRepository: LocalServiceRepository,
    private val remoteServiceRepository: RemoteServiceRepository
) {

    fun execute(id:Int){
        remoteServiceRepository.putActuallyStatisticsId(id)
        localServiceRepository.putActuallyStatisticsId(id)
    }

}