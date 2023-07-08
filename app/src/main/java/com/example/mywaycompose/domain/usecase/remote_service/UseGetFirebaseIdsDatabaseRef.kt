package com.example.mywaycompose.domain.usecase.remote_service

import com.example.mywaycompose.data.repository.service.DataRemoteServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.google.firebase.database.DatabaseReference

class UseGetFirebaseIdsDatabaseRef(
    private val remoteServiceRepository: RemoteServiceRepository
) {

    fun execute():DatabaseReference{
        return remoteServiceRepository.getIdsDatabaseReference()
    }

}