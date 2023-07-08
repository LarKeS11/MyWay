package com.example.mywaycompose.domain.usecase.subtasks

import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.repository.ideas.LocalIdeasRepository
import com.example.mywaycompose.domain.repository.ideas.RemoteIdeasRepository

class UseDeleteIdea(
    private val localIdeasRepository: LocalIdeasRepository,
    private val remoteIdeasRepository: RemoteIdeasRepository
) {

    suspend fun execute(idea:Idea){
        localIdeasRepository.deleteIdea(idea)
        remoteIdeasRepository.deleteIdea(idea)
    }

}