package com.example.mywaycompose.domain.repository.main_tasks

import com.example.mywaycompose.data.repository.firebase_models.FirebaseMainTask
import com.example.mywaycompose.domain.model.MainTask
import kotlinx.coroutines.flow.Flow

interface RemoteMainTasksRepository {
    fun pushMainTaskToDatabase(mainTask: MainTask)
    fun pushMainTaskImageFirebase(image:ByteArray, id:Int)
    fun deleteMainTask(mainTask: MainTask)
}