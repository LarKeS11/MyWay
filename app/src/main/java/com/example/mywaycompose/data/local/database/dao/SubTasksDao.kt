package com.example.mywaycompose.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mywaycompose.data.local.database.entity.SubTaskEntity
import com.example.mywaycompose.domain.model.SubTask


@Dao
interface SubTasksDao {

    @Insert
    suspend fun addSubtaskTask(subTaskEntity: SubTaskEntity)

    @Query("SELECT * FROM SubTaskEntity")
    suspend fun getAllSubTasks():List<SubTaskEntity>

    @Query("SELECT * FROM SubTaskEntity Where :id = mainTaskId")
    suspend fun getSubtasksByMainTaskId(id:Int):List<SubTaskEntity>

    @Query("SELECT * FROM SubTaskEntity Where :id = id")
    suspend fun getSubtaskById(id:Int): SubTaskEntity

    @Query("delete from SubTaskEntity where mainTaskId = :id")
    suspend fun deleteSubtasksByMainTaskId(id:Int)

}