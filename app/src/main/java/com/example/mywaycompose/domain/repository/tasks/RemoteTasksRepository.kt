package com.example.mywaycompose.domain.repository.tasks

import com.example.mywaycompose.data.repository.firebase_models.FirebaseTask
import com.example.mywaycompose.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface RemoteTasksRepository {
    fun pushTaskToDatabase(task: Task)
    fun deleteTask(task:Task)
}