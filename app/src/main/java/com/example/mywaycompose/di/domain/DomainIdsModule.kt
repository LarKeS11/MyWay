package com.example.mywaycompose.di.domain

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.usecase.*
import com.example.mywaycompose.domain.usecase.local_service.UseGetActuallyStatisticsId
import com.example.mywaycompose.domain.usecase.local_service.UseGetActuallySubtaskId
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskId
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallyMainTaskId
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallyStatisticsId
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallySubtaskId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DomainIdsModule {

    @Provides
    fun provideUsePutActuallyMainTaskId(
        localServiceRepository: LocalServiceRepository,
        remoteServiceRepository: RemoteServiceRepository
    ): UsePutActuallyMainTaskId {
        return UsePutActuallyMainTaskId(localServiceRepository,remoteServiceRepository)
    }

    @Provides
    fun provideUseGetMainTaskId(
        localServiceRepository: LocalServiceRepository
    ): UseGetMainTaskId {
        return UseGetMainTaskId(localServiceRepository)
    }

    @Provides
    fun provideUsePutActuallySubtaskId(
        localServiceRepository: LocalServiceRepository,
        remoteServiceRepository: RemoteServiceRepository
    ): UsePutActuallySubtaskId {
        return UsePutActuallySubtaskId(localServiceRepository,remoteServiceRepository)
    }

    @Provides
    fun provideUseGetActuallySubtaskId(
        localServiceRepository: LocalServiceRepository,
    ): UseGetActuallySubtaskId {
        return UseGetActuallySubtaskId(localServiceRepository)
    }

    @Provides
    fun provideUsePutActuallyStatisticsId(
        localServiceRepository: LocalServiceRepository,
        remoteServiceRepository: RemoteServiceRepository
    ): UsePutActuallyStatisticsId {
        return UsePutActuallyStatisticsId(localServiceRepository,remoteServiceRepository)
    }

    @Provides
    fun provideUseGetActuallyStatisticsId(
        localServiceRepository: LocalServiceRepository
    ): UseGetActuallyStatisticsId {
        return UseGetActuallyStatisticsId(localServiceRepository)
    }

}