package com.example.mywaycompose.domain.usecase.long_tasks

import android.annotation.SuppressLint
import android.util.Log
import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.repository.long_tasks.LocalLongTasksRepository
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class UseGetPointsOfLongTask(
    private val localLongTasksRepository: LocalLongTasksRepository
) {

    @SuppressLint("SimpleDateFormat")
    suspend fun execute(
        longTask: LongTask
    ):Float{


        val sdf =  SimpleDateFormat("dd-MM-yyyy")
        val date1 = sdf.parse(longTask.startDate!!)
        val date2 = sdf.parse(longTask.endDate!!)

        val diff =TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS)
        val points = localLongTasksRepository.getLongTaskStatById(longTask.id!!).size

        val res = (points) / diff.toFloat()

        Log.d("dsfdssfsdf",res.toString())

        return res
    }

}