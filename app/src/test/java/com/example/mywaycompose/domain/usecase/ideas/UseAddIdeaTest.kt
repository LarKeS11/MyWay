package com.example.mywaycompose.domain.usecase.ideas

import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.repository.FakeLocalIdeasRepository
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class UseAddIdeaTest{

    private lateinit var useAddIdea:UseAddIdea
    private lateinit var localIdeasRepository: FakeLocalIdeasRepository

    @Before
    fun setup(){
        localIdeasRepository = FakeLocalIdeasRepository()
        useAddIdea = UseAddIdea(localIdeasRepository)
    }

    @Test
    fun `add an idea to local database`(): Unit = runBlocking {

        val idea = Idea(
            id = 0,
            idea = "idea"
        )

        useAddIdea.execute(idea)

        localIdeasRepository.getIdeas().find { assertThat(it.id).equals(idea.id) }

    }

}