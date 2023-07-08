package com.example.mywaycompose.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mywaycompose.data.local.database.entity.TaskStatisticEntity

@Dao
interface TasksStatisticsDao {

    @Insert
    suspend fun addTaskToStatistic(taskStatisticEntity: TaskStatisticEntity)

    @Query("SELECT * FROM TaskStatisticEntity WHERE date = :date")
    suspend fun getTasksStatisticByDate(date:String):List<TaskStatisticEntity>

    @Query("SELECT * FROM TaskStatisticEntity")
    suspend fun getAllTasksStatistic():List<TaskStatisticEntity>

    @Query("SELECT * FROM TaskStatisticEntity WHERE mainTaskId = :id")
    suspend fun getStatisticByMainTaskId(id:Int):List<TaskStatisticEntity>

}