package com.example.mywaycompose.domain.usecase.tasks

import android.util.Log
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
class UseGetTaskById(val localTasksRepository: LocalTasksRepository) {

    operator fun invoke(id:Int): Flow<Resource<Task>> = flow {

        try {
            emit(Resource.Loading())
            val task = localTasksRepository.getTaskById(id)
            emit(Resource.Success(task))
        }catch (e:Exception){
            Log.d("use_get_task_by_id", e.toString())
            emit(Resource.Error("some"))
        }

    }

}