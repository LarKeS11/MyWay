package com.example.mywaycompose.di.domain

import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.usecase.local_service.UseCheckCorrectTime
import com.example.mywaycompose.domain.usecase.local_service.UseCompareDateWithCurrent
import com.example.mywaycompose.domain.usecase.local_service.UseGetActualityDate
import com.example.mywaycompose.domain.usecase.local_service.UseGetListOfMonthDays
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainDateModule {
    @Provides
    fun provideUseGetActualityDate(): UseGetActualityDate {
        return UseGetActualityDate()
    }

    @Provides
    fun provideUseGetListOfMonthDays(
        localServiceRepository: LocalServiceRepository
    ): UseGetListOfMonthDays {
        return UseGetListOfMonthDays(
            localServiceRepository = localServiceRepository
        )
    }

    @Provides
    fun provideUseCheckCorrectTime(
        localServiceRepository: LocalServiceRepository
    ): UseCheckCorrectTime {
        return UseCheckCorrectTime(localServiceRepository = localServiceRepository)
    }

    @Provides
    fun provideUseCompareDateWithCurrent(localServiceRepository: LocalServiceRepository): UseCompareDateWithCurrent {
        return UseCompareDateWithCurrent(localServiceRepository)
    }
}