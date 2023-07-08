package com.example.mywaycompose.di.domain

import com.example.mywaycompose.domain.repository.*
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.statistics.LocalStatisticsRepository
import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository
import com.example.mywaycompose.domain.usecase.statistics.UseAddTaskToStatistic
import com.example.mywaycompose.domain.usecase.statistics.UseGetAllTasksStatistic
import com.example.mywaycompose.domain.usecase.statistics.UseGetEffectiveStatisticValueFromFirebase
import com.example.mywaycompose.domain.usecase.statistics.UseGetEffectiveStatisticsByDayFromFirebase
import com.example.mywaycompose.domain.usecase.statistics.UseGetEffectiveStatisticsByDaysFromFirebase
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskStatistic
import com.example.mywaycompose.domain.usecase.statistics.UseAddTasksStatisticsFromFirebaseToLocalDatabase
import com.example.mywaycompose.domain.usecase.statistics.UseGetTasksStatisticByDate
import com.example.mywaycompose.domain.usecase.statistics.UseGetTasksStatisticsFromFirebase
import com.example.mywaycompose.domain.usecase.statistics.UsePushTaskToFirebaseStatistics
import com.example.mywaycompose.domain.usecase.statistics.UsePutCurrentDayEffectiveValue
import com.example.mywaycompose.domain.usecase.statistics.UsePutStatisticEffectiveValueToFirebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DomainStatisticsModule {

    @Provides
    fun provideUseAddTaskToStatistic(
        localStatisticsRepository: LocalStatisticsRepository,
        localServiceRepository: LocalServiceRepository,
        remoteServiceRepository: RemoteServiceRepository
    ): UseAddTaskToStatistic {
        return UseAddTaskToStatistic(localStatisticsRepository,localServiceRepository,remoteServiceRepository)
    }

    @Provides
    fun provideUseGetTasksStatisticByDate(localStatisticsRepository: LocalStatisticsRepository): UseGetTasksStatisticByDate {
        return UseGetTasksStatisticByDate(localStatisticsRepository)
    }

    @Provides
    fun provideUseGetAllTasksStatistic(
        localStatisticsRepository: LocalStatisticsRepository,
        localServiceRepository: LocalServiceRepository
    ): UseGetAllTasksStatistic {
        return UseGetAllTasksStatistic(
            localStatisticsRepository,
            localServiceRepository
        )
    }

    @Provides
    fun provideUseGetMainTaskStatistic(
        localStatisticsRepository: LocalStatisticsRepository,
        localServiceRepository: LocalServiceRepository
    ): UseGetMainTaskStatistic {
        return UseGetMainTaskStatistic(
            localStatisticsRepository,
            localServiceRepository
        )
    }

    @Provides
    fun provideUsePushTaskStatisticToFirebase(
        statisticsRepository: RemoteStatisticsRepository
    ): UsePushTaskToFirebaseStatistics {
        return UsePushTaskToFirebaseStatistics(statisticsRepository)
    }

    @Provides
    fun provideUsePutEffectiveValue(
        remoteStatisticsRepository: RemoteStatisticsRepository,
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ): UsePutStatisticEffectiveValueToFirebase {
        return UsePutStatisticEffectiveValueToFirebase(
            remoteStatisticsRepository,
            remoteServiceRepository,
            coroutineScope
        )
    }

    @Provides
    fun provideUseGetCurrentEffectiveValue(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ): UseGetEffectiveStatisticValueFromFirebase {
        return UseGetEffectiveStatisticValueFromFirebase(
            remoteServiceRepository = remoteServiceRepository,
            coroutineScope = coroutineScope
        )
    }

    @Provides
    fun provideUseGetEffectiveStatisticsByDays(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ): UseGetEffectiveStatisticsByDaysFromFirebase {
        return UseGetEffectiveStatisticsByDaysFromFirebase(remoteServiceRepository, coroutineScope)
    }

    @Provides
    fun provideUsePutDayEffectiveValue(
        remoteStatisticsRepository: RemoteStatisticsRepository
    ): UsePutCurrentDayEffectiveValue {
        return UsePutCurrentDayEffectiveValue(remoteStatisticsRepository)
    }

    @Provides
    fun provideUseGetStatisticsByDay(
        remoteStatisticsRepository: RemoteStatisticsRepository
    ): UseGetEffectiveStatisticsByDayFromFirebase {
        return UseGetEffectiveStatisticsByDayFromFirebase(remoteStatisticsRepository)
    }

    @Provides
    fun provideUseGetTasksStatisticsFromFirebase(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ):UseGetTasksStatisticsFromFirebase{
        return UseGetTasksStatisticsFromFirebase(
            remoteServiceRepository = remoteServiceRepository,
            coroutineScope = coroutineScope
        )
    }

    @Provides
    fun provideUseAddTasksStatisticsFromFirebaseToLocalDatabase(
        localStatisticsRepository: LocalStatisticsRepository
    ):UseAddTasksStatisticsFromFirebaseToLocalDatabase{
        return UseAddTasksStatisticsFromFirebaseToLocalDatabase(
            localStatisticsRepository = localStatisticsRepository
        )
    }


}