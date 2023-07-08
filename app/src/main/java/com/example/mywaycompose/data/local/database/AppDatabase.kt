package com.example.mywaycompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.mywaycompose.data.local.database.dao.IdeasDao
import com.example.mywaycompose.data.local.database.dao.LongTasksDao
import com.example.mywaycompose.data.local.database.dao.MainTasksDao
import com.example.mywaycompose.data.local.database.dao.SubTasksDao
import com.example.mywaycompose.data.local.database.dao.TasksDao
import com.example.mywaycompose.data.local.database.dao.TasksStatisticsDao
import com.example.mywaycompose.data.local.database.entity.*


@Database(entities = [TaskEntity::class, MainTaskEntity::class, SubTaskEntity::class, TaskStatisticEntity::class, LongTaskStatEntity::class, IdeaEntity::class, LongTaskEntity::class], version = 4)
abstract class AppDatabase:RoomDatabase() {
    abstract fun tasksDao(): TasksDao
    abstract fun mainTasksDao(): MainTasksDao
    abstract fun subTasksDao(): SubTasksDao
    abstract fun tasksStatisticDao(): TasksStatisticsDao
    abstract fun longTasksDao(): LongTasksDao
    abstract fun ideasDao(): IdeasDao
}