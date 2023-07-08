package com.example.mywaycompose.domain.usecase.main_tasks

import android.util.Log
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class UseGetAllMainTasks(
    private val localMainTasksRepository: LocalMainTasksRepository
) {

    operator fun invoke(): Flow<Resource<List<MainTask>>> = flow{
        try {
            emit(Resource.Loading())
            val tasks = localMainTasksRepository.getAllMainTasks()
            emit(Resource.Success(tasks))
        }catch (e:Exception){
            Log.d("sdfsdfsdfsdfsdfsdf",e.toString())
//            emit(Resource.Error(e.toString()))
        }

    }

}