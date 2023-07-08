package com.example.mywaycompose.data.local.database.service

import com.example.mywaycompose.data.local.database.entity.IdeaEntity
import com.example.mywaycompose.data.local.database.entity.LongTaskEntity
import com.example.mywaycompose.data.local.database.entity.LongTaskStatEntity
import com.example.mywaycompose.data.local.database.entity.MainTaskEntity
import com.example.mywaycompose.data.local.database.entity.SubTaskEntity
import com.example.mywaycompose.data.local.database.entity.TaskEntity
import com.example.mywaycompose.data.local.database.entity.TaskStatisticEntity
import com.example.mywaycompose.domain.model.LongTask

interface DatabaseServiceInterface {

    suspend fun addTask(task: TaskEntity)
    suspend fun getTasks(date:String):List<TaskEntity>
    suspend fun getTaskById(id:Int): TaskEntity
    suspend fun updateTask(task: TaskEntity)
    suspend fun isThereTheSameTask(time:String, date:String):Boolean
    suspend fun sortTasksByTime(tasks:List<TaskEntity>): List<TaskEntity>
    suspend fun deleteTask(task: TaskEntity)
    suspend fun getAllTasks():List<TaskEntity>
    suspend fun cleanTasksDatabase()

    suspend fun addMainTask(mainTaskEntity: MainTaskEntity)
    suspend fun getAllMainTasks():List<MainTaskEntity>
    suspend fun getMainTaskById(id:Int): MainTaskEntity
    suspend fun getMainTaskByTitle(title:String): MainTaskEntity
    suspend fun deleteMainTask(task: MainTaskEntity)
    suspend fun updateMainTask(task: MainTaskEntity)
    suspend fun deleteAllMainTasks()

    suspend fun addSubtask(subTaskEntity: SubTaskEntity)
    suspend fun getSubtasksByMainTaskId(id:Int):List<SubTaskEntity>
    suspend fun getSubtaskById(id:Int): SubTaskEntity
    suspend fun deleteSubtasksByMainTaskId(id:Int)

    suspend fun addTaskToStatistic(taskStatisticEntity: TaskStatisticEntity)
    suspend fun getTasksStatisticByDate(date:String):List<TaskStatisticEntity>
    suspend fun getAllTasksStatistic():List<TaskStatisticEntity>
    suspend fun getTasksStatisticByMainTaskId(id:Int):List<TaskStatisticEntity>

    suspend fun insertLongTaskStat(longTaskStat: LongTaskStatEntity)
    suspend fun getLongTaskStatById(id:Int):List<LongTaskStatEntity>

    suspend fun deleteAllTables()

    suspend fun addIdea(ideaEntity: IdeaEntity)
    suspend fun getIdeas():List<IdeaEntity>
    suspend fun deleteIdea(ideaEntity: IdeaEntity)

    suspend fun addLongTask(longTaskEntity: LongTaskEntity)
    suspend fun getLongTasks():List<LongTaskEntity>
    suspend fun deleteLongTask(longTask: LongTaskEntity)
    suspend fun findSameLongTaskStat(id:Int, date:String):Boolean

}