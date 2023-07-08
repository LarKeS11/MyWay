package com.example.mywaycompose.domain.repository.ideas

import com.example.mywaycompose.domain.model.Idea

interface LocalIdeasRepository {

    suspend fun addIdea(idea: Idea)
    suspend fun getIdeas():List<Idea>
    suspend fun deleteIdea(idea: Idea)

}