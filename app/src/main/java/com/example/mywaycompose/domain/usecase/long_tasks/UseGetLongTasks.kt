package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetLongTasks(
    private val localLongTasksRepository: LocalLongTasksRepository,
    private val localServiceRepository: LocalServiceRepository
) {

    operator fun invoke(date:String): Flow<Resource<List<LongTask>>> = flow {
        try {
            emit(Resource.Loading())
            val tasks = localLongTasksRepository.getLongTasks().filter {
                      localServiceRepository.checkDateInRange(it.startDate!!, it.endDate!!, date)
            }
            emit(Resource.Success(tasks))
        }catch (e:Exception){
        }
    }

}