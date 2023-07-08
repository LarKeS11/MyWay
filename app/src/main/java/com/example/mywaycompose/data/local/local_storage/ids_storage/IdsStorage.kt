package com.example.mywaycompose.data.local.local_storage.ids_storage

import android.content.Context
import android.util.Log
import javax.inject.Inject

class IdsStorage @Inject constructor(
    private val context: Context
): IdsStorageInterface {
    private val sharedPreferences = context.getSharedPreferences("id_storage", Context.MODE_PRIVATE)

    override fun putActuallyMainTaskId(id: Int) {
        sharedPreferences.edit().putInt("main_task_id",id).apply()
    }

    override fun getActuallyMainTaskId(): Int {
        return sharedPreferences.getInt("main_task_id",-1)
    }

    override fun putActuallySubtaskId(id: Int) {
        sharedPreferences.edit().putInt("subtask_task_id",id).apply()
    }

    override fun getActuallySubtaskId(): Int {
        return sharedPreferences.getInt("subtask_task_id",-1)
    }

    override fun putActuallyStatisticsId(id: Int) {
        sharedPreferences.edit().putInt("statistics_id",id).apply()
    }

    override fun getActuallyStatisticsId(): Int {
        return sharedPreferences.getInt("statistics_id",-1)
    }

    override fun putActuallyLongTaskId(id: Int) {
        Log.d("fsdfsdfsdf",id.toString())
        sharedPreferences.edit().putInt("long_task_id",id).apply()
    }

    override fun getActuallyLongTaskId(): Int {
        Log.d("fsdfsdfsdf",sharedPreferences.getInt("long_task_id",-1).toString())
        return sharedPreferences.getInt("long_task_id",-1)
    }


}