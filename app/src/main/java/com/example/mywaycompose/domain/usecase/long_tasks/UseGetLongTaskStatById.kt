package com.example.mywaycompose.domain.usecase.long_tasks

import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseGetLongTaskStatById @Inject constructor(
    private val localLongTasksRepository: LocalLongTasksRepository,
    private val localServiceRepository: LocalServiceRepository
) {

    operator fun invoke(id:Int, currentDate:String, taskStartedDate:String): Flow<Resource<List<Pair<String,Boolean>>>> = flow {

        try {
            emit(Resource.Loading())


            val stat = localLongTasksRepository.getLongTaskStatById(id).map { it.date }
            val days = localServiceRepository.getDatesInRange(taskStartedDate, currentDate)

            val output = days.map { Pair(it,it in stat) }

            emit(Resource.Success(output))

        }catch (e:Exception){
            emit(Resource.Error(e.toString()))
        }

    }

}