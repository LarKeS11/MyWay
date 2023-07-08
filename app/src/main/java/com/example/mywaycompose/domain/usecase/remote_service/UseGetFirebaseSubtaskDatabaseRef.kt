package com.example.mywaycompose.domain.usecase.remote_service

import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.google.firebase.database.DatabaseReference

class UseGetFirebaseSubtaskDatabaseRef(
    private val remoteServiceRepository: RemoteServiceRepository
) {

    fun execute():DatabaseReference{
        return remoteServiceRepository.getSubtasksDatabaseRef()
    }

}