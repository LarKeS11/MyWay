package com.example.mywaycompose.data.repository.ideas

import com.example.mywaycompose.data.local.database.entity.IdeaEntity
import com.example.mywaycompose.data.local.database.service.DatabaseService
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.repository.ideas.LocalIdeasRepository

class DataLocalIdeasRepository(
    private val databaseService: DatabaseService
): LocalIdeasRepository {
    override suspend fun addIdea(idea: Idea) {
        databaseService.addIdea(idea.toIdeaEntity())
    }

    override suspend fun getIdeas(): List<Idea> {
        return databaseService.getIdeas().map {
            it.toIdea()
        }
    }

    override suspend fun deleteIdea(idea: Idea) {
        databaseService.deleteIdea(idea.toIdeaEntity())
    }


}

fun Idea.toIdeaEntity(): IdeaEntity {
    return IdeaEntity(
        id = this.id,
        idea = this.idea
    )
}

fun IdeaEntity.toIdea():Idea{
    return Idea(
        id = this.id,
        idea = this.idea
    )
}