package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseGetActuallySubtaskId(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute():Int{
        return localServiceRepository.getActuallySubtaskId() + 1
    }

}