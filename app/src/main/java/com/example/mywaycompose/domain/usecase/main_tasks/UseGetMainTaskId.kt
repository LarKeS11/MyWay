package com.example.mywaycompose.domain.usecase.main_tasks

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseGetMainTaskId(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute():Int{
        return localServiceRepository.getActuallyMainTaskId() + 1
    }

}