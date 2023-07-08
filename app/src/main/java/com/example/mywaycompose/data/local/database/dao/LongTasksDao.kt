package com.example.mywaycompose.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mywaycompose.data.local.database.entity.LongTaskEntity
import com.example.mywaycompose.data.local.database.entity.LongTaskStatEntity
import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.LongTaskStat

@Dao
interface LongTasksDao {

    @Insert
    suspend fun insertLongTask(longTaskEntity: LongTaskEntity)
    @Query("SELECT * FROM LongTaskEntity")
    suspend fun getLongTasks():List<LongTaskEntity>

    @Delete
    suspend fun deleteLongTask(longTask: LongTaskEntity)
    @Insert
    suspend fun insertLongTaskStat(longTaskStatEntity: LongTaskStatEntity)

    @Query("SELECT * FROM LongTaskStatEntity Where idTask = :id")
    suspend fun getLongTaskStatById(id:Int):List<LongTaskStatEntity>

    @Query("SELECT * FROM LongTaskStatEntity Where idTask = :id and date = :date")
    suspend fun findSameStat(id:Int, date:String):LongTaskStatEntity?

}