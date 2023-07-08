package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import java.time.LocalDate

class UseCompareDateWithCurrent(
    private val localServiceRepository: LocalServiceRepository
) {

    fun execute(date:LocalDate, kind:Int):Boolean{
        return localServiceRepository.compareDateWithCurrent(date, kind)
    }

}