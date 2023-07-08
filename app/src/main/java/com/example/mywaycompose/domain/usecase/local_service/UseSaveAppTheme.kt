package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseSaveAppTheme(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute(theme:String){
        localServiceRepository.saveAppTheme(theme)
    }

}