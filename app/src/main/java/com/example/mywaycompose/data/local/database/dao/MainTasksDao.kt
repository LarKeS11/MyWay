package com.example.mywaycompose.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mywaycompose.data.local.database.entity.MainTaskEntity
import com.example.mywaycompose.domain.model.MainTask

@Dao
interface MainTasksDao {

    @Insert
    suspend fun addMainTask(mainTaskEntity: MainTaskEntity)

    @Query("SELECT * FROM MainTaskEntity")
    suspend fun getAllMainTasks():List<MainTaskEntity>

    @Query("SELECT * FROM MainTaskEntity WHERE :id = id")
    suspend fun getMainTaskById(id:Int): MainTaskEntity

    @Query("SELECT * FROM MainTaskEntity WHERE :title = title")
    suspend fun getMainTaskByTitle(title:String): MainTaskEntity

    @Delete
    suspend fun deleteMainTask(task: MainTaskEntity)

    @Update
    suspend fun updateMainTask(task: MainTaskEntity)

    @Query ("Delete from MainTaskEntity")
    suspend fun deleteAllMainTasks()
}