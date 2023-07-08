package com.example.mywaycompose.di.domain

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository
import com.example.mywaycompose.domain.usecase.*
import com.example.mywaycompose.domain.usecase.local_service.UseClearDatabase
import com.example.mywaycompose.domain.usecase.long_tasks.UseGetLongTasks
import com.example.mywaycompose.domain.usecase.tasks.UseAddTask
import com.example.mywaycompose.domain.usecase.tasks.UseDeleteTask
import com.example.mywaycompose.domain.usecase.tasks.UseGetTaskById
import com.example.mywaycompose.domain.usecase.tasks.UseGetTasks
import com.example.mywaycompose.domain.usecase.tasks.UseCheckSameTasks
import com.example.mywaycompose.domain.usecase.tasks.UseGetTasksFromFirebase
import com.example.mywaycompose.domain.usecase.tasks.UsePushTaskToFirebase
import com.example.mywaycompose.domain.usecase.tasks.UseToCompleteTask
import com.example.mywaycompose.domain.usecase.tasks.UseUpdateTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DomainTasksModule {

    @Provides
    fun provideUseAddTask(localTasksRepository: LocalTasksRepository): UseAddTask {
        return UseAddTask(localTasksRepository)
    }

    @Provides
    fun provideUsePushTaskToFirebase(
        remoteTasksRepository: RemoteTasksRepository
    ): UsePushTaskToFirebase {
        return UsePushTaskToFirebase(remoteTasksRepository)
    }

    @Provides
    fun provideUseGetTasks(
        localTasksRepository: LocalTasksRepository,
        localServiceRepository: LocalServiceRepository
    ): UseGetTasks {
        return UseGetTasks(
            localTasksRepository,
            localServiceRepository
        )
    }


    @Provides
    fun provideUseGetTaskById(localTasksRepository: LocalTasksRepository): UseGetTaskById {
        return UseGetTaskById(localTasksRepository)
    }

    @Provides
    fun provideUseUpdateTask(localTasksRepository: LocalTasksRepository): UseUpdateTask {
        return UseUpdateTask(localTasksRepository)
    }

    @Provides
    fun provideUseIsThereTheSameTask(localTasksRepository: LocalTasksRepository): UseCheckSameTasks {
        return UseCheckSameTasks(localTasksRepository)
    }

    @Provides
    fun provideUseDeleteTask(localTasksRepository: LocalTasksRepository, remoteTasksRepository: RemoteTasksRepository): UseDeleteTask {
        return UseDeleteTask(localTasksRepository,remoteTasksRepository)
    }

    @Provides
    fun provideUseToCompleteTask(
        localTasksRepository: LocalTasksRepository,
        remoteTasksRepository: RemoteTasksRepository
    ): UseToCompleteTask {
        return UseToCompleteTask(
           localTasksRepository = localTasksRepository,
            remoteTasksRepository = remoteTasksRepository
        )
    }


    @Provides
    fun provideUseDeleteAllTables(
        localTasksRepository: LocalTasksRepository
    ): UseClearDatabase {
        return UseClearDatabase(localTasksRepository)
    }


    @Provides
    fun provideUseGetTaskByTaskFromFirebase(
        coroutineScope: CoroutineScope,
        remoteServiceRepository: RemoteServiceRepository
    ):UseGetTasksFromFirebase{
        return UseGetTasksFromFirebase(remoteServiceRepository, coroutineScope)
    }

}