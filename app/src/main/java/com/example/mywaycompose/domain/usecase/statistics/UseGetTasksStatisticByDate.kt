package com.example.mywaycompose.domain.usecase.statistics

import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.statistics.LocalStatisticsRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetTasksStatisticByDate(
    private val localStatisticsRepository: LocalStatisticsRepository
) {

    operator fun invoke(date:String): Flow<Resource<List<TaskStatistic>>> = flow{

        try {
            emit(Resource.Loading())
            val statistic = localStatisticsRepository.getTasksStatisticByDate(date)
            emit(Resource.Success(statistic))
        }catch (e:Exception){
            emit(Resource.Error(""))
        }

    }

}