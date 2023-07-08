package com.example.mywaycompose.domain.usecase.statistics

import com.example.mywaycompose.domain.model.EffectiveStat
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository
import com.example.mywaycompose.utils.Constants.EFFECTIVE_STAT_KIND
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UsePutStatisticEffectiveValueToFirebase(
    private val remoteStatisticsRepository: RemoteStatisticsRepository,
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    fun execute(effective: EffectiveStat, day:String = ""){
        remoteServiceRepository.getAppData<Int>(EFFECTIVE_STAT_KIND).onEach {last ->
            var total = effective.priority + 1
            total += if(effective.appliedMainTask) 1 else  0
            total += if(effective.appliedSubtask) 1 else  0
            remoteStatisticsRepository.putStatisticCurrentEffectiveValue(total + last[0])
            if(day.isNotEmpty()){
                remoteStatisticsRepository.getEffectiveStatisticsByDay(day).onEach {
                    remoteStatisticsRepository.putCurrentDayEffective(day,it + total)
                }.launchIn(coroutineScope)
            }

        }.launchIn(coroutineScope)

    }

}