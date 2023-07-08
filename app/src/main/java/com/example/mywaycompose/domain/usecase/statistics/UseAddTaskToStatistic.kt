package com.example.mywaycompose.domain.usecase.statistics

import android.util.Log
import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.statistics.LocalStatisticsRepository

class UseAddTaskToStatistic(
    private val localStatisticsRepository: LocalStatisticsRepository,
    private val localServiceRepository: LocalServiceRepository,
    private val remoteServiceRepository: RemoteServiceRepository
) {

    suspend fun execute(taskStatistic: TaskStatistic){
        try {

            val task_stat_id = localServiceRepository.getActuallyStatisticsId() + 1
            remoteServiceRepository.putActuallyStatisticsId(task_stat_id)
            localServiceRepository.putActuallyStatisticsId(task_stat_id)

            taskStatistic.id = task_stat_id

            localStatisticsRepository.addTaskToStatistic(taskStatistic)
        }catch (e:Exception){
            Log.d("sdfgsdfsdf",e.toString())
        }
    }

}