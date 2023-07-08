package com.example.mywaycompose.di.app

import android.content.Context
import com.example.mywaycompose.domain.usecase.local_service.UseGetAppTheme
import com.example.mywaycompose.domain.usecase.local_service.UseSaveAppTheme
import com.example.mywaycompose.presentation.MainActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    fun provideContext(@ApplicationContext appContext: Context):Context{
        return appContext
    }

    @Provides
    fun provideMainActivityViewModel(
        useGetAppTheme: UseGetAppTheme,
        useSaveAppTheme: UseSaveAppTheme
    ):MainActivityViewModel{
        return MainActivityViewModel(
            useGetAppTheme = useGetAppTheme,
            useSaveAppTheme = useSaveAppTheme
        )
    }

}