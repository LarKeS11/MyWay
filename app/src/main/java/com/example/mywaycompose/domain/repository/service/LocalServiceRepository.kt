package com.example.mywaycompose.domain.repository.service

import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.time.LocalDate

interface LocalServiceRepository {
    fun saveFirstDate(date:String)
    fun getFirstDate():String

    fun getCountOfDaysByDate(year:Int, month:Int): Int
    fun getCurrentMonthName(month:Int):String
    fun checkTimeCorrect(time:String):Boolean
    fun getCurrentDate(): LocalDate
    fun dateToDayOfWeek(localDate: LocalDate):String
    fun compareDateWithCurrent(localDate: LocalDate, kind:Int):Boolean
    fun checkDateInRange(firstDate:String, secondDate:String, checkableDate:String):Boolean
    fun getDatesInRange(firstDate: String, secondDate: String):List<String>

    fun convertUriToBitmap(uri: Uri): Bitmap
    fun convertBitmapToFile(bitmap: Bitmap): File
    fun getTypeOfImage(uri: Uri):String
    suspend fun saveImage(uri: Uri, id:Int)
    fun getImageFileById(id:Int): File
    fun convertFileToByteArray(file: File):ByteArray
    fun deleteFile(file: File)
    fun saveByteArrayAsFile(id:Int, byteArray: ByteArray)

    fun putActuallyMainTaskId(id:Int)
    fun getActuallyMainTaskId():Int
    fun putActuallySubtaskId(id:Int)
    fun getActuallySubtaskId():Int
    fun putActuallyStatisticsId(id:Int)
    fun getActuallyStatisticsId():Int
    fun putActuallyLongTaskId(id:Int)
    fun getActuallyLongTaskId():Int

    fun saveAppTheme(theme:String)
    fun getAppTheme():String
}