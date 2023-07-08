package com.example.mywaycompose.domain.usecase.main_tasks

import android.net.Uri
import android.util.Log
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.repository.main_tasks.LocalMainTasksRepository
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UseAddFullMainTask(
    private val localMainTasksRepository: LocalMainTasksRepository,
    private val localServiceRepository: LocalServiceRepository
    ) {

    operator fun invoke(mainTask: MainTask): Flow<Resource<String>> = flow{
        try {
            emit(Resource.Loading())
            localMainTasksRepository.addMainTask(mainTask)
            if (mainTask.imageSrc.isNotEmpty()){
                val uri = Uri.parse(mainTask.imageSrc)
                localServiceRepository.saveImage(uri, id = mainTask.id!!)
                val fileImage = localServiceRepository.getImageFileById(mainTask.id!!)
                mainTask.imageSrc = fileImage.absolutePath
            }
            localMainTasksRepository.updateMainTask(mainTask)
            emit(Resource.Success(mainTask.id.toString()))
        }catch (e:Exception){
            Log.d("use_add_main_task", e.toString())
            emit(Resource.Error(e.toString()))
        }

    }

}