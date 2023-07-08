package com.example.mywaycompose.di.data

import android.content.Context
import com.example.mywaycompose.data.local.local_storage.auth_storage.AuthLocalStorage
import com.example.mywaycompose.data.local.local_storage.auth_storage.AuthLocalStorageInterface
import com.example.mywaycompose.data.local.local_storage.ids_storage.IdsStorage
import com.example.mywaycompose.data.local.local_storage.image_storage.ImageStorage
import com.example.mywaycompose.data.local.local_storage.image_storage.ImageStorageInterface
import com.example.mywaycompose.data.local.local_storage.theme_storage.ThemeStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataLocalStorageModule {

    @Provides
    @Singleton
    fun provideAuthLocalStorage(context: Context): AuthLocalStorageInterface {
        return AuthLocalStorage(context)
    }

    @Provides
    @Singleton
    fun provideImageStorage(context: Context): ImageStorageInterface {
        return ImageStorage(context)
    }


    @Provides
    @Singleton
    fun provideIdsStorage(context: Context): IdsStorage {
        return IdsStorage(context)
    }

    @Provides
    @Singleton
    fun provideThemeStorage(context: Context):ThemeStorage{
        return ThemeStorage(context)
    }

}