package com.example.mywaycompose.domain.usecase.local_service

import android.net.Uri
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import javax.inject.Inject

class UseGetTypeOfImage @Inject constructor(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute(uri: Uri):String{
        return localServiceRepository.getTypeOfImage(uri)
    }

}