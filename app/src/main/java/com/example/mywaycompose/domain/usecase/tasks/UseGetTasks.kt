package com.example.mywaycompose.domain.usecase.tasks

import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.tasks.LocalTasksRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class UseGetTasks @Inject constructor(
    private val localTasksRepository: LocalTasksRepository,
    private val localServiceRepository: LocalServiceRepository
    ) {

      operator fun invoke(date:String):Flow<Resource<List<Task>>> = flow{

          try {
              emit(Resource.Loading())

              emit(Resource.Success(localTasksRepository.getTasks(date)))
          }catch (e:Exception){
              emit(Resource.Success(listOf()))
          }

     }

}