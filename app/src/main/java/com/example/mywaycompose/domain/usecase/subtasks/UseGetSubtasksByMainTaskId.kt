package com.example.mywaycompose.domain.usecase.subtasks

import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UseGetSubtasksByMainTaskId(
    private val localSubtasksRepository: LocalSubtasksRepository
) {

    operator fun invoke(id:Int):Flow<Resource<List<SubTask>>> = flow {

        try {
            emit(Resource.Loading())
            val subtasks = localSubtasksRepository.getSubtasksByMainTaskId(id)
            emit(Resource.Success(subtasks))
        }catch (e:Exception){
            emit(Resource.Error(e.toString()))
        }

    }

}