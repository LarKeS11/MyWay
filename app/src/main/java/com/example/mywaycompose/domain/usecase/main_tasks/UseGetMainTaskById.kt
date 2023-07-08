package com.example.mywaycompose.domain.usecase.main_tasks

import android.util.Log
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UseGetMainTaskById(
    private val localMainTasksRepository: LocalMainTasksRepository
) {
    operator fun invoke(id:Int): Flow<Resource<MainTask>> = flow{

        try {
            emit(Resource.Loading())
            val mainTask = localMainTasksRepository.getMainTaskById(id)
            Log.d("sgfdgdfgdfdg",mainTask.idIdea.toString())
            emit(Resource.Success(mainTask))
        }catch (e:Exception){
            Log.d("use_get_task_by_id", e.toString())
            emit(Resource.Error("Error"))
        }

    }
}