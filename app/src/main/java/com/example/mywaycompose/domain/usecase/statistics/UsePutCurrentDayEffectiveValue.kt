package com.example.mywaycompose.domain.usecase.statistics

import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository

class UsePutCurrentDayEffectiveValue(
    private val remoteStatisticsRepository: RemoteStatisticsRepository
) {

    fun execute(date:String, value:Int){
        remoteStatisticsRepository.putCurrentDayEffective(date, value)
    }

}