package com.example.mywaycompose.domain.usecase.subtasks

import android.util.Log
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.repository.subtasks.LocalSubtasksRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetSubtaskById(
    private val localSubtasksRepository: LocalSubtasksRepository
) {
    operator fun invoke(id:Int): Flow<Resource<SubTask>> = flow{
        emit(Resource.Loading())
        try {
            val subtask = localSubtasksRepository.getSubtaskById(id)
            emit(Resource.Success(subtask))
        }catch (e:Exception){
            Log.d("dfgbvcdfgb",e.toString())
        }

    }
}