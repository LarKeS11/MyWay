package com.example.mywaycompose.domain.repository.subtasks

import com.example.mywaycompose.domain.model.SubTask

interface LocalSubtasksRepository {

    suspend fun addSubtask(subTask: SubTask)
    suspend fun getSubtasksByMainTaskId(id:Int):List<SubTask>
    suspend fun getSubtaskById(id:Int):SubTask
    suspend fun deleteSubtasksByMainTaskId(id:Int)

}