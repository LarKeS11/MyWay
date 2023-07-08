package com.example.mywaycompose.di.domain

import androidx.core.content.PermissionChecker.PermissionResult
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.domain.usecase.long_tasks.*
import com.example.mywaycompose.domain.usecase.statistics.UseAddLongTaskToStatistics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DomainLongTasksModule {

    @Provides
    fun provideUseGetLongTaskStatById(
        localLongTasksRepository: LocalLongTasksRepository,
        localServiceRepository: LocalServiceRepository
    ): UseGetLongTaskStatById {
        return UseGetLongTaskStatById(
            localLongTasksRepository,
            localServiceRepository
        )
    }

    @Provides
    fun provideUseInsertLongTaskStat(
        localLongTasksRepository: LocalLongTasksRepository
    ): UseAddLongTaskToStatistics {
        return UseAddLongTaskToStatistics(localLongTasksRepository)
    }

    @Provides
    fun provideUseGetLongTasks(
        localLongTasksRepository: LocalLongTasksRepository,
        localServiceRepository: LocalServiceRepository
    ):UseGetLongTasks{
        return UseGetLongTasks(localLongTasksRepository, localServiceRepository)
    }

    @Provides
    fun provideUseAddLongTask(
        localLongTasksRepository: LocalLongTasksRepository,
        remoteLongTasksRepository: RemoteLongTasksRepository
    ):UseAddLongTask{
        return UseAddLongTask(localLongTasksRepository,remoteLongTasksRepository)
    }

    @Provides
    fun provideUseDeleteLongTask(
        localLongTasksRepository: LocalLongTasksRepository,
        remoteLongTasksRepository:RemoteLongTasksRepository
    ):UseDeleteLongTask{
        return UseDeleteLongTask(localLongTasksRepository,remoteLongTasksRepository)
    }

    @Provides
    fun provideUseAddLongTaskToFirebase(
        remoteLongTasksRepository: RemoteLongTasksRepository
    ):UsePushLongTaskToFirebase{
        return UsePushLongTaskToFirebase(remoteLongTasksRepository)
    }

    @Provides
    fun provideUseDeleteLongTaskFromFirebase(
        remoteLongTasksRepository: RemoteLongTasksRepository
    ):UseDeleteLongTaskFromFirebase{
        return UseDeleteLongTaskFromFirebase(remoteLongTasksRepository)
    }

    @Provides
    fun provideUseGetLongTasksFromFirebase(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ):UseGetLongTasksFromFirebase{
        return UseGetLongTasksFromFirebase(
            remoteServiceRepository,
            coroutineScope
        )
    }

    @Provides
    fun provideUseCheckLongTask(
        localLongTasksRepository: LocalLongTasksRepository,
        remoteLongTasksRepository: RemoteLongTasksRepository
    ):UseCheckLongTask{
        return UseCheckLongTask(
            localLongTasksRepository = localLongTasksRepository,
            remoteLongTasksRepository = remoteLongTasksRepository
        )
    }

    @Provides
    fun provideUseGetActuallyLongTaskId(
        localServiceRepository: LocalServiceRepository
    ):UseGetActuallyLongTaskId{
        return UseGetActuallyLongTaskId(localServiceRepository)
    }

    @Provides
    fun provideUsePutActuallyLongTaskId(
        localServiceRepository: LocalServiceRepository,
        remoteServiceRepository: RemoteServiceRepository
    ):UsePutActuallyLongTaskId{
        return UsePutActuallyLongTaskId(
            localServiceRepository = localServiceRepository,
            remoteServiceRepository = remoteServiceRepository
        )
    }

    @Provides
    fun provideUseGetLongTasksStatFromFirebase(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ):UseGetLongTasksStatFromFirebase{
        return UseGetLongTasksStatFromFirebase(
            remoteServiceRepository,
            coroutineScope
        )
    }

}