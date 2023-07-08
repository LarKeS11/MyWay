package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseGetFirstDate(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute():String{
        return localServiceRepository.getFirstDate()
    }

}