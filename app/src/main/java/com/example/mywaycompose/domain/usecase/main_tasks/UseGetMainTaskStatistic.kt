package com.example.mywaycompose.domain.usecase.main_tasks

import android.util.Log
import com.example.mywaycompose.data.repository.service.toLocalDate
import com.example.mywaycompose.domain.model.TaskStatistic
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.domain.repository.statistics.LocalStatisticsRepository
import com.example.mywaycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.util.*

class UseGetMainTaskStatistic(
    private val localStatisticsRepository: LocalStatisticsRepository,
    private val localServiceRepository: LocalServiceRepository
) {

    private fun getSumOfArray(list:List<TaskStatistic>):Int{
        return list.size
    }

    operator fun invoke(id:Int): Flow<Resource<List<Pair<String, Float>>>> = flow {
        try {
            emit(Resource.Loading())

            val tasks = localStatisticsRepository.getAllTasksStatistic().filter {
                Log.d("srtggg","$id  ${it.mainTaskId}")
                it.mainTaskId == id
            }

            val firstDate = localServiceRepository.getFirstDate().toLocalDate().minusDays(1)
            var nowDate = localServiceRepository.getCurrentDate()

            val days = mutableListOf<Pair<LocalDate, List<TaskStatistic>>>()

            Log.d("tasks_lqweol", tasks.toString())

            if(tasks.isEmpty()) {
                emit(Resource.Success(emptyList()))
                return@flow
            }

            while (nowDate != firstDate){
                val local = tasks.filter{ it.date.toLocalDate() == nowDate }
                days.add(Pair(nowDate, local))
                nowDate = nowDate.minusDays(1)
            }

            val scopes = days.map { getSumOfArray(it.second) }
            val maxValue = Collections.max(scopes)

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING

            val output = days.map {
                Pair(
                    localServiceRepository.dateToDayOfWeek(it.first),
                    if(maxValue == 0) 0.0f else getSumOfArray(it.second) / maxValue.toFloat()
                )
            }

            emit(Resource.Success(output))

        }catch (e:Exception){
            Log.d("excepltion",e.toString())
        }
    }

}