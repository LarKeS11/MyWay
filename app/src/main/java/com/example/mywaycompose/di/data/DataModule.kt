package com.example.mywaycompose.di.data


import com.example.mywaycompose.data.local.database.service.DatabaseService
import com.example.mywaycompose.data.local.local_storage.auth_storage.AuthLocalStorageInterface
import com.example.mywaycompose.data.local.local_storage.ids_storage.IdsStorage
import com.example.mywaycompose.data.local.local_storage.image_storage.ImageStorageInterface
import com.example.mywaycompose.data.local.local_storage.theme_storage.ThemeStorage
import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.data.repository.*
import com.example.mywaycompose.data.repository.ideas.DataLocalIdeasRepository
import com.example.mywaycompose.data.repository.ideas.DataRemoteIdeasRepository
import com.example.mywaycompose.data.repository.long_tasks.DataLocalLongTasksRepository
import com.example.mywaycompose.data.repository.long_tasks.DataRemoteLongTasksRepository
import com.example.mywaycompose.data.repository.main_tasks.DataLocalMainTasksRepository
import com.example.mywaycompose.data.repository.main_tasks.DataRemoteMainTasksRepository
import com.example.mywaycompose.data.repository.service.DataLocalServiceRepository
import com.example.mywaycompose.data.repository.service.DataRemoteServiceRepository
import com.example.mywaycompose.data.repository.statistics.DataLocalStatisticsRepository
import com.example.mywaycompose.data.repository.statistics.DataRemoteStatisticsRepository
import com.example.mywaycompose.data.repository.subtasks.DataLocalSubtasksRepository
import com.example.mywaycompose.data.repository.subtasks.DataRemoteSubtasksRepository
import com.example.mywaycompose.data.repository.tasks.DataLocalTasksRepository
import com.example.mywaycompose.data.repository.tasks.DataRemoteTasksRepository
import com.example.mywaycompose.domain.repository.*
import com.example.mywaycompose.domain.repository.ideas.LocalIdeasRepository
import com.example.mywaycompose.domain.repository.ideas.RemoteIdeasRepository
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import com.example.mywaycompose.domain.repository.long_tasks.RemoteLongTasksRepository
import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository
import com.example.mywaycompose.domain.repository.main_tasks.RemoteMainTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.statistics.LocalStatisticsRepository
import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository
import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository
import com.example.mywaycompose.domain.repository.subtasks.RemoteSubtasksRepository
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.domain.repository.tasks.RemoteTasksRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    fun provideDataRemoteTasksRepository(firebaseService: FirebaseService): RemoteTasksRepository {
        return DataRemoteTasksRepository(firebaseService)
    }

    @Provides
    fun provideDataRemoteMainTasksRepository(firebaseService: FirebaseService): RemoteMainTasksRepository {
        return DataRemoteMainTasksRepository(firebaseService)
    }

    @Provides
    fun provideDataRemoteSubtasksRepository(firebaseService: FirebaseService): RemoteSubtasksRepository {
        return DataRemoteSubtasksRepository(firebaseService)
    }

    @Provides
    fun provideDataTasksRepository(databaseService: DatabaseService): LocalTasksRepository {
        return DataLocalTasksRepository(databaseService)
    }

    @Provides
    fun provideDataRemoteStatisticsRepository(firebaseService: FirebaseService): RemoteStatisticsRepository {
        return DataRemoteStatisticsRepository(firebaseService)
    }

    @Provides
    fun provideDataRemoteIdeasRepository(firebaseService: FirebaseService): RemoteIdeasRepository {
        return DataRemoteIdeasRepository(firebaseService)
    }

    @Provides
    fun provideLocalServiceRepository(
        authLocalStorage: AuthLocalStorageInterface,
        imageStorageInterface: ImageStorageInterface,
        idsStorage: IdsStorage,
        themeStorage: ThemeStorage
    ):LocalServiceRepository{
        return DataLocalServiceRepository(
            authLocalStorage = authLocalStorage,
            imageStorage = imageStorageInterface,
            idsStorage = idsStorage,
            themeStorage = themeStorage
        )
    }

    @Provides
    fun provideRemoteServiceRepository(
        firebaseService: FirebaseService
    ):RemoteServiceRepository{
        return DataRemoteServiceRepository(firebaseService)
    }


    @Provides
    fun provideDataMainTasksRepository(
        databaseService: DatabaseService
    ): LocalMainTasksRepository {
        return DataLocalMainTasksRepository(databaseService)
    }

    @Provides
    fun provideDataSubtasksRepository(
        databaseService: DatabaseService
    ): LocalSubtasksRepository {
        return DataLocalSubtasksRepository(databaseService)
    }

    @Provides
    fun provideDataStatisticsRepository(
        databaseService: DatabaseService
    ): LocalStatisticsRepository {
        return DataLocalStatisticsRepository(
            databaseService
        )
    }

    @Provides
    fun provideDataLongTasksRepository(
        databaseService: DatabaseService
    ): LocalLongTasksRepository {
        return DataLocalLongTasksRepository(databaseService)
    }

    @Provides
    fun provideDataRemoteLongTasksRepository(
        firebaseService: FirebaseService
    ):RemoteLongTasksRepository{
        return DataRemoteLongTasksRepository(firebaseService)
    }


    @Provides
    fun provideDataIdeasRepository(
        databaseService: DatabaseService
    ): LocalIdeasRepository {
        return DataLocalIdeasRepository(
            databaseService = databaseService
        )
    }

    @Singleton
    @Provides
    fun provideCoroutineContext():CoroutineContext{
        return Job()
    }

    @Singleton
    @Provides
    fun provideCoroutineScope(
        context:CoroutineContext
    ):CoroutineScope{
        return CoroutineScope(context + SupervisorJob())
    }


}