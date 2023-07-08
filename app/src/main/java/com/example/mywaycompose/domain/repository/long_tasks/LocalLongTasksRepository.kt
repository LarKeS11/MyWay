package com.example.mywaycompose.domain.repository.long_tasks

import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.LongTaskStat

interface LocalLongTasksRepository {

    suspend fun insertLongTaskStat(longTaskStat: LongTaskStat)
    suspend fun getLongTaskStatById(id:Int):List<LongTaskStat>

    suspend fun addLongTask(longTask: LongTask)
    suspend fun getLongTasks():List<LongTask>

    suspend fun deleteLongTask(longTask: LongTask)
    suspend fun findSameStat(id:Int, date:String):Boolean
}