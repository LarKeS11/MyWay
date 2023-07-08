package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseGetAppTheme(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute():String{
        return localServiceRepository.getAppTheme()
    }

}