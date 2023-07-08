package com.example.mywaycompose.domain.repository.statistics

import com.example.mywaycompose.data.repository.firebase_models.FirebaseTaskStat
import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteStatisticsRepository {
    fun pushTaskStatistic(taskStatistic: TaskStatistic)
    fun putStatisticCurrentEffectiveValue(value:Int)
    fun putCurrentDayEffective(date:String, value:Int)
    fun getEffectiveStatisticsByDay(date:String):Flow<Int>
}