package com.example.mywaycompose.domain.repository.subtasks

import com.example.mywaycompose.data.repository.firebase_models.FirebaseSubtask
import com.example.mywaycompose.domain.model.SubTask
import kotlinx.coroutines.flow.Flow

interface RemoteSubtasksRepository {
    fun pushSubtaskToDatabase(subTask: SubTask)
    fun cleanSubtasksByMainTaskId(id:Int)
}