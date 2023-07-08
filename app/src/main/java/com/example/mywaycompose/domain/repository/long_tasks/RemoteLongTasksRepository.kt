package com.example.mywaycompose.domain.repository.long_tasks

import com.example.mywaycompose.data.repository.firebase_models.FirebaseLongTask
import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.LongTaskStat
import kotlinx.coroutines.flow.Flow


interface RemoteLongTasksRepository {

    fun addLongTask(longTask: LongTask)
    fun deleteLongTask(longTask: LongTask)
    fun addLongTaskStat(longTaskStat: LongTaskStat)

}