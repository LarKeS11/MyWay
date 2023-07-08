package com.example.mywaycompose.domain.usecase.remote_service

import com.example.mywaycompose.data.repository.service.toDateString
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository

class UsePutStartUsingDateToFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute(){
        remoteServiceRepository.putStartUsingDate(localServiceRepository.getCurrentDate().toDateString())
    }

}