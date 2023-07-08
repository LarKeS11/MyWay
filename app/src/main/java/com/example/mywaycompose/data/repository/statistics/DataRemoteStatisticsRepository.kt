package com.example.mywaycompose.data.repository.statistics

import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.data.repository.firebase_models.FirebaseTaskStat
import com.example.mywaycompose.data.repository.firebase_models.toResponseFirebaseTaskStat
import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow

class DataRemoteStatisticsRepository(
    private val firebaseService: FirebaseService
): RemoteStatisticsRepository {
    override fun pushTaskStatistic(taskStatistic: TaskStatistic) {
        firebaseService.pushTaskStatistic(taskStatistic.toResponseFirebaseTaskStat())
    }

    override fun putStatisticCurrentEffectiveValue(value: Int) {
        firebaseService.putCurrentEffectiveValue(value)
    }

    override fun putCurrentDayEffective(date: String, value: Int) {
        firebaseService.putCurrentDayEffective(date, value)
    }

    override fun getEffectiveStatisticsByDay(date: String): Flow<Int> {
        return firebaseService.getEffectiveStatisticsByDay(date)
    }

}