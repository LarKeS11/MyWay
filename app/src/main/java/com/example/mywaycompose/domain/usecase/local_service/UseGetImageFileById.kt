package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import java.io.File

class UseGetImageFileById(private val localServiceRepository: LocalServiceRepository) {
    fun execute(id:Int):File{
        return localServiceRepository.getImageFileById(id)
    }
}