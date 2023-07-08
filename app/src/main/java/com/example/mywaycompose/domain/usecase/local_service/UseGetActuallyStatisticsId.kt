package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class  UseGetActuallyStatisticsId(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute():Int{
        return localServiceRepository.getActuallyStatisticsId()
    }

}