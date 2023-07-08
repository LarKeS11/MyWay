package com.example.mywaycompose.data.local.local_storage.ids_storage

interface IdsStorageInterface {

    fun putActuallyMainTaskId(id:Int)
    fun getActuallyMainTaskId():Int
    fun putActuallySubtaskId(id:Int)
    fun getActuallySubtaskId():Int
    fun putActuallyStatisticsId(id:Int)
    fun getActuallyStatisticsId():Int
    fun putActuallyLongTaskId(id:Int)
    fun getActuallyLongTaskId():Int

}