package com.example.mywaycompose.di.domain

import com.example.mywaycompose.data.repository.service.DataLocalServiceRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.usecase.remote_service.UseAuthGoogleWithFirebase
import com.example.mywaycompose.domain.usecase.remote_service.UseGetAuthFirebaseSession
import com.example.mywaycompose.domain.usecase.remote_service.UseGetFirebaseIdsDatabaseRef
import com.example.mywaycompose.domain.usecase.remote_service.UseGetFirebaseMainTasksRef
import com.example.mywaycompose.domain.usecase.remote_service.UseGetFirebaseSubtaskDatabaseRef
import com.example.mywaycompose.domain.usecase.remote_service.UseGetFirebaseTasksRef
import com.example.mywaycompose.domain.usecase.remote_service.UseGetFirebaseTasksStatisticsDatabaseRef
import com.example.mywaycompose.domain.usecase.remote_service.UseGetGoogleSignInSetup
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTasksImageRef
import com.example.mywaycompose.domain.usecase.remote_service.UseGetCurrentIdByModelKind
import com.example.mywaycompose.domain.usecase.remote_service.UseGetStartUsingDate
import com.example.mywaycompose.domain.usecase.remote_service.UsePutStartUsingDateToFirebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DomainRemoteServiceModule {
    @Provides
    fun provideUseGetGoogleSignInSetup(
        remoteServiceRepository: RemoteServiceRepository
    ): UseGetGoogleSignInSetup {
        return UseGetGoogleSignInSetup(
            remoteServiceRepository
        )
    }

    @Provides
    fun provideUseAuthGoogleWithFirebase(
        remoteServiceRepository: RemoteServiceRepository
    ): UseAuthGoogleWithFirebase {
        return UseAuthGoogleWithFirebase(remoteServiceRepository)
    }

    @Provides
    fun provideUseGetFirebaseAuthSession(
        remoteServiceRepository: RemoteServiceRepository
    ): UseGetAuthFirebaseSession {
        return UseGetAuthFirebaseSession(remoteServiceRepository)
    }

    @Provides
    fun provideUseGetMainTasksFirebaseRef(
        remoteServiceRepository: RemoteServiceRepository
    ): UseGetFirebaseMainTasksRef {
        return UseGetFirebaseMainTasksRef(
           remoteServiceRepository
        )
    }

    @Provides
    fun provideGetMainTasksImageRef(
        remoteServiceRepository: RemoteServiceRepository
    ): UseGetMainTasksImageRef {
        return UseGetMainTasksImageRef(remoteServiceRepository)
    }


    @Provides
    fun provideUseGetIdsDatabaseReference(
        remoteServiceRepository: RemoteServiceRepository
    ): UseGetFirebaseIdsDatabaseRef {
        return UseGetFirebaseIdsDatabaseRef(remoteServiceRepository)
    }

    @Provides
    fun provideUseGetFirebaseSubtasksDatabaseRef(
        remoteServiceRepository: RemoteServiceRepository
    ): UseGetFirebaseSubtaskDatabaseRef {
        return UseGetFirebaseSubtaskDatabaseRef(remoteServiceRepository)
    }

    @Provides
    fun provideUseGetFirebaseTasksStatisticsDatabaseRef(
        remoteServiceRepository: RemoteServiceRepository
    ): UseGetFirebaseTasksStatisticsDatabaseRef {
        return UseGetFirebaseTasksStatisticsDatabaseRef(remoteServiceRepository)
    }
    @Provides
    fun provideUseGetFirebaseTasksRef(
        remoteServiceRepository: RemoteServiceRepository
    ): UseGetFirebaseTasksRef {
        return UseGetFirebaseTasksRef(remoteServiceRepository)
    }

    @Provides
    fun provideUseGetCurrentIdByModel(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ): UseGetCurrentIdByModelKind{
        return UseGetCurrentIdByModelKind(
            remoteServiceRepository = remoteServiceRepository,
            coroutineScope = coroutineScope
        )
    }

    @Provides
    fun provideUsePutStartUsingDate(
        remoteServiceRepository: RemoteServiceRepository,
        localServiceRepository: LocalServiceRepository
    ):UsePutStartUsingDateToFirebase{
        return UsePutStartUsingDateToFirebase(
            remoteServiceRepository = remoteServiceRepository,
            localServiceRepository = localServiceRepository
        )
    }

    @Provides
    fun provideUseGetStartUsingDate(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ):UseGetStartUsingDate{
        return UseGetStartUsingDate(
            remoteServiceRepository = remoteServiceRepository,
            coroutineScope = coroutineScope
        )
    }

}