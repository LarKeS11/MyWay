package com.example.mywaycompose.di.data

import android.content.Context
import androidx.room.Room
import com.example.mywaycompose.data.local.database.AppDatabase
import com.example.mywaycompose.data.local.database.service.DatabaseService
import com.example.mywaycompose.data.local.database.dao.IdeasDao
import com.example.mywaycompose.data.local.database.dao.LongTasksDao
import com.example.mywaycompose.data.local.database.dao.MainTasksDao
import com.example.mywaycompose.data.local.database.dao.SubTasksDao
import com.example.mywaycompose.data.local.database.dao.TasksDao
import com.example.mywaycompose.data.local.database.dao.TasksStatisticsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataDatabaseModule {

    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,"myway_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideTasksDao(database: AppDatabase): TasksDao {
        return database.tasksDao()
    }

    @Provides
    fun provideMainTasksDao(database: AppDatabase): MainTasksDao {
        return database.mainTasksDao()
    }

    @Provides
    fun provideSubTasksDao(database: AppDatabase): SubTasksDao {
        return database.subTasksDao()
    }

    @Provides
    fun provideIdeasDao(database: AppDatabase): IdeasDao {
        return database.ideasDao()
    }

    @Provides
    fun provideTasksStatisticDao(database: AppDatabase): TasksStatisticsDao {
        return database.tasksStatisticDao()
    }

    @Provides
    fun provideLongTasksDao(database: AppDatabase): LongTasksDao {
        return database.longTasksDao()
    }

    @Provides
    fun provideDatabaseRepository(
        tasksDao: TasksDao,
        mainTasksDao: MainTasksDao,
        subTasksDao: SubTasksDao,
        tasksStatisticsDao: TasksStatisticsDao,
        longTasksDao: LongTasksDao,
        context: Context,
        ideasDao: IdeasDao
    ): DatabaseService {
        return DatabaseService(
            tasksDao,
            mainTasksDao,
            subTasksDao,
            tasksStatisticsDao,
            longTasksDao,
            context,
            ideasDao
        )
    }




}