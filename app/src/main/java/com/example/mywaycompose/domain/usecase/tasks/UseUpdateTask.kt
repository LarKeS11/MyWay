package com.example.mywaycompose.domain.usecase.tasks

import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UseUpdateTask(
    val localTasksRepository: LocalTasksRepository
) {

    operator fun invoke(task:Task): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            localTasksRepository.updateTask(task)
            emit(Resource.Success(true))
        }catch (e:Exception){
            emit(Resource.Error(""))
        }
    }

}