package com.example.mywaycompose.domain.usecase.local_service

import android.net.Uri
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseSaveImage(private val localServiceRepository: LocalServiceRepository) {
    suspend fun execute(uri:Uri, id:Int){
        return localServiceRepository.saveImage(uri, id)
    }
}