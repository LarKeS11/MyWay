package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.LongTaskStat
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository

class UseCheckLongTask(
    private val localLongTasksRepository: LocalLongTasksRepository,
    private val remoteLongTasksRepository: RemoteLongTasksRepository
) {

    suspend fun execute(
        longTaskStat: LongTaskStat,
        init:Boolean = false
    ){
        if(localLongTasksRepository.findSameStat(longTaskStat.idTask, longTaskStat.date)) return
        if(!init) remoteLongTasksRepository.addLongTaskStat(longTaskStat)
        localLongTasksRepository.insertLongTaskStat(longTaskStat)
    }

}