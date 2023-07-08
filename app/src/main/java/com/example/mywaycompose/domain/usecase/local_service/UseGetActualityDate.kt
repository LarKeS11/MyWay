package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.model.DateServer
import com.example.mywaycompose.domain.model.toDateServer
import java.text.SimpleDateFormat
import java.util.*

class UseGetActualityDate {
    fun execute():DateServer{
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = Date()
        val current = formatter.format(date)

        return current.toDateServer()

    }
}