package com.example.mywaycompose.data.repository.main_tasks

import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.data.repository.firebase_models.FirebaseMainTask
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.model.toFirebaseMainTask
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository
import kotlinx.coroutines.flow.Flow

class DataRemoteMainTasksRepository(
    private val firebaseService: FirebaseService
): RemoteMainTasksRepository {
    override fun pushMainTaskToDatabase(mainTask: MainTask) {
        firebaseService.pushMainTaskToDatabase(mainTask.toFirebaseMainTask())
    }

    override fun pushMainTaskImageFirebase(image: ByteArray, id: Int) {
        firebaseService.pushMainTaskImageFirebase(image, id)
    }

    override fun deleteMainTask(mainTask: MainTask) {
        firebaseService.deleteMainTask(mainTask.toFirebaseMainTask())
    }

}