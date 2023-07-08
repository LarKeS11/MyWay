package com.example.mywaycompose.di.domain

import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository
import com.example.mywaycompose.domain.repository.subtasks.RemoteSubtasksRepository
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository
import com.example.mywaycompose.domain.usecase.*
import com.example.mywaycompose.domain.usecase.subtasks.UseAddSubtask
import com.example.mywaycompose.domain.usecase.subtasks.UseDeleteSubtasksByMainTaskId
import com.example.mywaycompose.domain.usecase.subtasks.UseDeleteTasksDependsOnSubtask
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtaskById
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtasksByMainTaskId
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtasksFromFirebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DomainSubTasksModule {

    @Provides
    fun provideUseAddSubtask(
        localSubtasksRepository: LocalSubtasksRepository,
        remoteSubtasksRepository: RemoteSubtasksRepository
    ): UseAddSubtask {
        return UseAddSubtask(localSubtasksRepository,remoteSubtasksRepository)
    }
    @Provides
    fun provideUseGetSubtasksByMainTaskId(localSubtasksRepository: LocalSubtasksRepository): UseGetSubtasksByMainTaskId {
        return UseGetSubtasksByMainTaskId(localSubtasksRepository)
    }
    @Provides
    fun provideUseGetSubtaskById(localSubtasksRepository: LocalSubtasksRepository): UseGetSubtaskById {
        return UseGetSubtaskById(localSubtasksRepository)
    }
    @Provides
    fun provideUseDeleteSubtasksByMainTaskId(
        localSubtasksRepository: LocalSubtasksRepository,
        remoteSubtasksRepository: RemoteSubtasksRepository
    ): UseDeleteSubtasksByMainTaskId {
        return UseDeleteSubtasksByMainTaskId(localSubtasksRepository,remoteSubtasksRepository)
    }

    @Provides
    fun provideUseGetSubtasksFromFirebase(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ):UseGetSubtasksFromFirebase{
        return UseGetSubtasksFromFirebase(
            remoteServiceRepository = remoteServiceRepository,
            coroutineScope = coroutineScope
        )
    }

    @Provides
    fun provideUseDeleteTasksBySubtaskId(
        remoteTasksRepository: RemoteTasksRepository,
        localTasksRepository: LocalTasksRepository
    ):UseDeleteTasksDependsOnSubtask{
        return UseDeleteTasksDependsOnSubtask(
            remoteTasksRepository = remoteTasksRepository,
            tasksLocalRepository =  localTasksRepository
        )
    }

}