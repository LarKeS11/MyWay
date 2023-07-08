package com.example.mywaycompose.data.repository.ideas

import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.data.repository.firebase_models.toResponseFirebaseIdea
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.repository.ideas.RemoteIdeasRepository
import kotlinx.coroutines.flow.Flow

class DataRemoteIdeasRepository(
    private val firebaseService: FirebaseService
): RemoteIdeasRepository {
    override fun pushIdea(idea: Idea) {
        firebaseService.pushIdea(idea.toResponseFirebaseIdea())
    }

    override fun deleteIdea(idea: Idea) {
        firebaseService.deleteIdea(idea.toResponseFirebaseIdea())
    }


}