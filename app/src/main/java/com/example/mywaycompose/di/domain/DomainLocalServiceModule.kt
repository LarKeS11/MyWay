package com.example.mywaycompose.di.domain

import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.usecase.local_service.UseGetAppTheme
import com.example.mywaycompose.domain.usecase.local_service.UseGetFirstDate
import com.example.mywaycompose.domain.usecase.local_service.UseGetImageFileById
import com.example.mywaycompose.domain.usecase.local_service.UseGetTypeOfImage
import com.example.mywaycompose.domain.usecase.local_service.UseParseUriToFile
import com.example.mywaycompose.domain.usecase.local_service.UseSaveAppTheme
import com.example.mywaycompose.domain.usecase.local_service.UseSaveFirstDate
import com.example.mywaycompose.domain.usecase.local_service.UseSaveImage
import com.example.mywaycompose.domain.usecase.long_tasks.UseGetPointsOfLongTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLocalServiceModule {

    @Provides
    fun provideUseSaveFirstDate(localServiceRepository: LocalServiceRepository): UseSaveFirstDate {
        return UseSaveFirstDate(localServiceRepository)
    }

    @Provides
    fun provideUseGetFirstDate(localServiceRepository: LocalServiceRepository): UseGetFirstDate {
        return UseGetFirstDate(localServiceRepository)
    }

    @Provides
    fun provideUseParseUriToFile(localServiceRepository: LocalServiceRepository): UseParseUriToFile {
        return UseParseUriToFile(localServiceRepository = localServiceRepository)
    }

    @Provides
    fun provideUseGetTypeOfImage(localServiceRepository: LocalServiceRepository): UseGetTypeOfImage {
        return UseGetTypeOfImage(localServiceRepository)
    }

    @Provides
    fun provideUseSaveImage(localServiceRepository: LocalServiceRepository): UseSaveImage {
        return UseSaveImage(localServiceRepository)
    }
    @Provides
    fun provideUseGetImageFileById(localServiceRepository: LocalServiceRepository): UseGetImageFileById {
        return UseGetImageFileById(localServiceRepository)
    }

    @Provides
    fun provideUseSaveAppTheme(localServiceRepository: LocalServiceRepository):UseSaveAppTheme{
        return UseSaveAppTheme(localServiceRepository)
    }

    @Provides
    fun provideUseGetAppTheme(localServiceRepository: LocalServiceRepository):UseGetAppTheme{
        return UseGetAppTheme(localServiceRepository)
    }

    @Provides
    fun provideUseGetPointsOfLongTask(
        localLongTasksRepository: LocalLongTasksRepository
    ):UseGetPointsOfLongTask{
        return UseGetPointsOfLongTask(localLongTasksRepository)
    }


}