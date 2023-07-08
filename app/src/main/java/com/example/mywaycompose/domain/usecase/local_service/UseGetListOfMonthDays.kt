package com.example.mywaycompose.domain.usecase.local_service

import com.example.mywaycompose.domain.model.DateServer
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import javax.inject.Inject

class UseGetListOfMonthDays @Inject constructor(
    private val localServiceRepository: LocalServiceRepository
) {
    fun execute(dateServer: DateServer):List<Pair<DateServer, String>>{
        val countOfDays = localServiceRepository.getCountOfDaysByDate(year = dateServer.year.toInt(), month = dateServer.month.toInt())
        val monthName = localServiceRepository.getCurrentMonthName(dateServer.month.toInt())
        val outputDays = ArrayList<Pair<DateServer, String>>()

        for(i in 1..countOfDays){
            val date = DateServer(
                year = dateServer.year,
                month = dateServer.month,
                day = if(i < 10) "0$i" else i.toString()
            )
            outputDays.add(Pair(date, monthName))
        }
        return outputDays
    }
}