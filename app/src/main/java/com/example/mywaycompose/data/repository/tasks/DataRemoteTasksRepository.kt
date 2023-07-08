package com.example.mywaycompose.data.repository.tasks

import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.data.repository.firebase_models.FirebaseTask
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.model.toFirebaseTask
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository
import kotlinx.coroutines.flow.Flow

class DataRemoteTasksRepository(
    private val firebaseService: FirebaseService
): RemoteTasksRepository {
    override fun pushTaskToDatabase(task: Task) {
        firebaseService.pushTaskToDatabase(task.toFirebaseTask())
    }

    override fun deleteTask(task: Task) {
        firebaseService.deleteTask(task.toFirebaseTask())
    }

}