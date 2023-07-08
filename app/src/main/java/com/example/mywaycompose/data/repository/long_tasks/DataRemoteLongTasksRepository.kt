package com.example.mywaycompose.data.repository.long_tasks

import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.data.repository.firebase_models.FirebaseLongTask
import com.example.mywaycompose.data.repository.firebase_models.toFirebaseLongTask
import com.example.mywaycompose.data.repository.firebase_models.toFirebaseLongTaskStat
import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.LongTaskStat
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository
import kotlinx.coroutines.flow.Flow

class DataRemoteLongTasksRepository(
    private val firebaseService: FirebaseService
):RemoteLongTasksRepository {
    override fun addLongTask(longTask: LongTask) {
        firebaseService.addLongTask(longTask.toFirebaseLongTask())
    }

    override fun deleteLongTask(longTask: LongTask) {
        firebaseService.deleteLongTask(longTask.toFirebaseLongTask())
    }

    override fun addLongTaskStat(longTaskStat: LongTaskStat) {
        firebaseService.pushLongTaskStatToDatabase(longTaskStat.toFirebaseLongTaskStat())
    }

}