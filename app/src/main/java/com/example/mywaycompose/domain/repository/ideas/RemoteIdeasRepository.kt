package com.example.mywaycompose.domain.repository.ideas

import com.example.mywaycompose.domain.model.Idea
import kotlinx.coroutines.flow.Flow

interface RemoteIdeasRepository {
    fun pushIdea(idea: Idea)
    fun deleteIdea(idea:Idea)
}