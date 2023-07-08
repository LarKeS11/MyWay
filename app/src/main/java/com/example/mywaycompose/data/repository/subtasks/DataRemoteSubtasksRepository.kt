package com.example.mywaycompose.data.repository.subtasks

import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.data.repository.firebase_models.FirebaseSubtask
import com.example.mywaycompose.data.repository.firebase_models.toResponseFirebaseSubtask
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.repository.subtasks.RemoteSubtasksRepository
import kotlinx.coroutines.flow.Flow

class DataRemoteSubtasksRepository(
    private val firebaseService: FirebaseService
): RemoteSubtasksRepository {
    override fun pushSubtaskToDatabase(subTask: SubTask) {
        firebaseService.pushSubtaskToDatabase(subTask.toResponseFirebaseSubtask())
    }

    override fun cleanSubtasksByMainTaskId(id: Int) {
        firebaseService.cleanSubtasksByMainTaskId(id)
    }

}