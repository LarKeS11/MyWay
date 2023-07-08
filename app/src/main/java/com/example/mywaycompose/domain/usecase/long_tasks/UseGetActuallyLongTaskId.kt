package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseGetActuallyLongTaskId(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute() = localServiceRepository.getActuallyLongTaskId() + 1

}