package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseCheckCorrectTime(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute(time:String):Boolean{
        return localServiceRepository.checkTimeCorrect(time)
    }

}