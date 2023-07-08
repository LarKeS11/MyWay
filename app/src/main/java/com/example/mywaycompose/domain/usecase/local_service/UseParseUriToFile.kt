package com.example.mywaycompose.domain.usecase.local_service

import android.net.Uri
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import java.io.File
import javax.inject.Inject

class UseParseUriToFile @Inject constructor(
    private val localServiceRepository: LocalServiceRepository
) {
    fun execute(uri: Uri): File{
        val bitmap = localServiceRepository.convertUriToBitmap(uri)
        return localServiceRepository.convertBitmapToFile(bitmap)
    }
}