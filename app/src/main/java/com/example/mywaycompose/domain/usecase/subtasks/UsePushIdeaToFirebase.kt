package com.example.mywaycompose.domain.usecase.subtasks

import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.repository.ideas.RemoteIdeasRepository

class UsePushIdeaToFirebase(
    private val remoteIdeasRepository: RemoteIdeasRepository
) {

    fun execute(idea:Idea){
        remoteIdeasRepository.pushIdea(idea)
    }

}