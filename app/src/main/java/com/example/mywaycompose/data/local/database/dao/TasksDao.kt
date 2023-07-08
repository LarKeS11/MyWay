package com.example.mywaycompose.data.local.database.dao

import androidx.room.*
import com.example.mywaycompose.data.local.database.entity.TaskEntity

@Dao
interface TasksDao {
    @Insert
    suspend fun addTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE DATE=:date")
    suspend fun getTasksByDate(date:String):List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE ID = :id")
    suspend fun getTaskById(id:Int): TaskEntity

    @Delete
    suspend fun removeTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE time = :time AND date = :date")
    suspend fun isThereTheSame(time:String,date:String): TaskEntity?

    @Query("SELECT * FROM TaskEntity")
    suspend fun getAllTasks():List<TaskEntity>

    @Query("DELETE FROM TaskEntity")
    suspend fun cleanTasksDatabase()
}