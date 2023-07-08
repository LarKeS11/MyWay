package com.example.mywaycompose.domain.usecase.remote_service

import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.google.firebase.database.DatabaseReference

class UseGetFirebaseMainTasksRef(
    private val remoteServiceRepository: RemoteServiceRepository
) {

    fun execute():DatabaseReference{
        return remoteServiceRepository.getMainTasksDatabaseRef()
    }

}