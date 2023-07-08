package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository

class UseSaveFirstDate(
    private val localServiceRepository: LocalServiceRepository
) {

     fun execute(date:String){
         localServiceRepository.saveFirstDate(date)
    }

}