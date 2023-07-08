package com.example.mywaycompose.di.domain

import com.example.mywaycompose.domain.repository.ideas.LocalIdeasRepository
import com.example.mywaycompose.domain.repository.ideas.RemoteIdeasRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.usecase.ideas.UseAddIdea
import com.example.mywaycompose.domain.usecase.subtasks.UseDeleteIdea
import com.example.mywaycompose.domain.usecase.ideas.UseGetIdeas
import com.example.mywaycompose.domain.usecase.ideas.UseGetIdeasFromFirebase
import com.example.mywaycompose.domain.usecase.subtasks.UsePushIdeaToFirebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DomainIdeasRepository {

    @Provides
    fun provideAddIdea(localIdeasRepository: LocalIdeasRepository): UseAddIdea {
        return UseAddIdea(
            localIdeasRepository = localIdeasRepository
        )
    }

    @Provides
    fun provideGetIdeas(localIdeasRepository: LocalIdeasRepository): UseGetIdeas {
        return UseGetIdeas(localIdeasRepository = localIdeasRepository)
    }

    @Provides
    fun provideDeleteIdea(
        localIdeasRepository: LocalIdeasRepository,
        remoteIdeasRepository: RemoteIdeasRepository
    ): UseDeleteIdea {
        return UseDeleteIdea(localIdeasRepository,remoteIdeasRepository)
    }

    @Provides
    fun provideUsePushIdeaFirebase(
        remoteIdeasRepository: RemoteIdeasRepository
    ): UsePushIdeaToFirebase {
        return UsePushIdeaToFirebase(remoteIdeasRepository)
    }

    @Provides
    fun provideUseGetIdeasFirebase(
        remoteServiceRepository: RemoteServiceRepository,
        coroutineScope: CoroutineScope
    ): UseGetIdeasFromFirebase {
        return UseGetIdeasFromFirebase(remoteServiceRepository,coroutineScope)
    }

}