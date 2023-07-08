package com.example.mywaycompose.domain.usecase.ideas

import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.repository.ideas.LocalIdeasRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UseGetIdeas(
    private val localIdeasRepository: LocalIdeasRepository
) {

    operator fun invoke():Flow<Resource<List<Idea>>> = flow {
        emit(Resource.Loading())
        try {
            val ideas = localIdeasRepository.getIdeas()
            emit(Resource.Success(ideas))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }

    }

}