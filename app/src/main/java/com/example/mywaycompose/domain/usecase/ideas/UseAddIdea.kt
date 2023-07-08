package com.example.mywaycompose.domain.usecase.ideas

import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.repository.ideas.LocalIdeasRepository

class UseAddIdea(
    private val localIdeasRepository: LocalIdeasRepository
    ) {

    suspend fun execute(idea:Idea){
        localIdeasRepository.addIdea(idea)
    }

}