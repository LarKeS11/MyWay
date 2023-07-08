package com.example.mywaycompose.di.domain

import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository
import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.usecase.*
import com.example.mywaycompose.domain.usecase.main_tasks.UseAddFullMainTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseAddShortMainTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseDeleteAllMainTasks
import com.example.mywaycompose.domain.usecase.main_tasks.UseDeleteMainTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetAllMainTasks
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskById
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskFromFirebase
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskIdByTitle
import com.example.mywaycompose.domain.usecase.main_tasks.UsePushMainTaskImageToFirebase
import com.example.mywaycompose.domain.usecase.main_tasks.UsePushMainTaskToFirebase
import com.example.mywaycompose.domain.usecase.main_tasks.UseSaveImageFromFirebase
import com.example.mywaycompose.domain.usecase.main_tasks.UseUpdateMainTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DomainMainTasksModule {
    @Provides
    fun provideUseAddMainTask(
        localMainTasksRepository: LocalMainTasksRepository,
        localServiceRepository: LocalServiceRepository
    ): UseAddFullMainTask {
        return UseAddFullMainTask(
            localMainTasksRepository = localMainTasksRepository,
            localServiceRepository = localServiceRepository
        )
    }

    @Provides
    fun provideUsePushMainTaskToFirebase(
        remoteMainTasksRepository: RemoteMainTasksRepository
    ): UsePushMainTaskToFirebase {
        return UsePushMainTaskToFirebase(remoteMainTasksRepository)
    }

    @Provides
    fun provideUsePushMainTaskImageToFirebase(
        remoteMainTasksRepository: RemoteMainTasksRepository,
        localServiceRepository: LocalServiceRepository
    ): UsePushMainTaskImageToFirebase {
        return UsePushMainTaskImageToFirebase(remoteMainTasksRepository,localServiceRepository)
    }

    @Provides
    fun provideUseGetAllMainTasks(
        localMainTasksRepository: LocalMainTasksRepository
    ): UseGetAllMainTasks {
        return UseGetAllMainTasks(localMainTasksRepository)
    }
    @Provides
    fun provideUseGetMainTaskById(
        localMainTasksRepository: LocalMainTasksRepository
    ): UseGetMainTaskById {
        return UseGetMainTaskById(localMainTasksRepository)
    }
    @Provides
    fun provideUseGetMainTaskIdByTitle(
        localMainTasksRepository: LocalMainTasksRepository
    ): UseGetMainTaskIdByTitle {
        return UseGetMainTaskIdByTitle(localMainTasksRepository)
    }
    @Provides
    fun provideUseDeleteMainTask(
        localMainTasksRepository: LocalMainTasksRepository,
        localSubtasksRepository: LocalSubtasksRepository,
        remoteMainTasksRepository: RemoteMainTasksRepository
    ): UseDeleteMainTask {
        return UseDeleteMainTask(
            localMainTasksRepository,
            localSubtasksRepository,
            remoteMainTasksRepository
        )
    }
    @Provides
    fun provideUseUpdateMainTask(
        localMainTasksRepository: LocalMainTasksRepository,
        localServiceRepository: LocalServiceRepository,
        remoteServiceRepository: RemoteServiceRepository,
        remoteMainTasksRepository: RemoteMainTasksRepository
    ): UseUpdateMainTask {
        return UseUpdateMainTask(
            localMainTasksRepository,
            localServiceRepository,
            remoteServiceRepository,
            remoteMainTasksRepository
        )
    }
    @Provides
    fun provideUseDeleteAllMainTasks(
        localServiceRepository: LocalServiceRepository,
        localMainTasksRepository: LocalMainTasksRepository
    ): UseDeleteAllMainTasks {
        return UseDeleteAllMainTasks(
            localServiceRepository = localServiceRepository,
            localMainTasksRepository = localMainTasksRepository
        )
    }

    @Provides
    fun provideUseAddMainTaskFromFirebase(
        localMainTasksRepository: LocalMainTasksRepository
    ): UseAddShortMainTask {
        return UseAddShortMainTask(localMainTasksRepository)
    }

    @Provides
    fun provideUseGetMainTasksFromFirebase(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ):UseGetMainTaskFromFirebase{
        return UseGetMainTaskFromFirebase(
            remoteServiceRepository = remoteServiceRepository,
            coroutineScope =  coroutineScope
        )
    }

    @Provides
    fun provideUseSaveImageFromFirebase(
        remoteServiceRepository: RemoteServiceRepository,
        localServiceRepository: LocalServiceRepository
    ):UseSaveImageFromFirebase{
        return UseSaveImageFromFirebase(
            remoteServiceRepository = remoteServiceRepository,
            localServiceRepository = localServiceRepository
        )
    }
}