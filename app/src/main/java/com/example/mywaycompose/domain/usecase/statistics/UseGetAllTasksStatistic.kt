package com.example.mywaycompose.domain.usecase.statistics

import android.util.Log
import com.example.mywaycompose.data.repository.service.toLocalDate
import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.statistics.LocalStatisticsRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.util.Collections.max

class UseGetAllTasksStatistic(
    private val localStatisticsRepository: LocalStatisticsRepository,
    private val localServiceRepository: LocalServiceRepository
) {

    private fun getSumOfArray(list:List<TaskStatistic>):Int{
        return list.size
        }

    operator fun invoke(kind:Int): Flow<Resource<List<Pair<String, Float>>>> = flow {

        try{
            emit(Resource.Loading())

            var tasks = localStatisticsRepository.getAllTasksStatistic()



            if(kind == 1) tasks = tasks.filter { it.mainTaskId != -1 }
            else tasks = tasks.filter { it.mainTaskId == -1 }

            val firstDate = localServiceRepository.getFirstDate().toLocalDate().minusDays(1)
            var nowDate = localServiceRepository.getCurrentDate()

            val days = mutableListOf<Pair<LocalDate, List<TaskStatistic>>>()


            if(tasks.isEmpty()) {
                emit(Resource.Success(emptyList()))
                return@flow
            }

            while (nowDate != firstDate){
                val local = tasks.filter{
                    it.date.toLocalDate() == nowDate
                }
                days.add(Pair(nowDate, local))
                nowDate = nowDate.minusDays(1)
            }

            Log.d("gdfgdfgdfg","${days.size}")

            val scopes = days.map { getSumOfArray(it.second) }
            val maxValue = max(scopes)

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING

            val output = days.map {
                    Pair(
                        localServiceRepository.dateToDayOfWeek(it.first),
                        if(maxValue == 0) 0.0f else getSumOfArray(it.second).toFloat()
                    )
            }



            emit(Resource.Success(output.reversed()))

        }catch (e:Exception){
        }

    }

}