package com.example.mywaycompose.data.repository.service

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.example.mywaycompose.data.local.local_storage.auth_storage.AuthLocalStorageInterface
import com.example.mywaycompose.data.local.local_storage.ids_storage.IdsStorage
import com.example.mywaycompose.data.local.local_storage.image_storage.ImageStorageInterface
import com.example.mywaycompose.data.local.local_storage.theme_storage.ThemeStorage
import com.example.mywaycompose.domain.repository.service.LocalServiceRepository
import com.example.mywaycompose.utils.Constants
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class DataLocalServiceRepository(
    private val authLocalStorage: AuthLocalStorageInterface,
    private val imageStorage: ImageStorageInterface,
    private val idsStorage: IdsStorage,
    private val themeStorage: ThemeStorage
):LocalServiceRepository {

    override fun saveFirstDate(date: String) {
        authLocalStorage.saveFirstDate(date)
    }

    override fun getFirstDate(): String {
        return authLocalStorage.getFirstDate()
    }

    override fun getCountOfDaysByDate(year: Int, month: Int): Int {
        val yearMonthObject = YearMonth.of(year, month) // TODO: fix getting days of month
        return yearMonthObject.lengthOfMonth()
    }

    override fun getCurrentMonthName(month: Int): String {
        when(month){
            1 -> return "January"
            2 -> return "February"
            3 -> return "March"
            4 -> return "April"
            5 -> return "May"
            6 -> return "June"
            7 -> return "July"
            8 -> return "August"
            9 -> return "September"
            10 -> return "October"
            11 -> return "November"
            12 -> return "December"
            else -> return "None"

        }
    }

    override fun checkTimeCorrect(time: String): Boolean {
        Log.d("parse_time",time)
        return try{
            val parser = DateTimeFormatter.ofPattern(if(time.length == 5) "HH:mm" else "H:mm")
            val localTime = LocalTime.parse(time, parser)
            true
        }catch (e:Exception){
            Log.d("parse_time",e.toString())
            false
        }

    }

    override fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }

    override fun dateToDayOfWeek(localDate: LocalDate): String {
        return when(localDate.dayOfWeek.value){
            1 -> "Mn"
            2 -> "Tue"
            3 -> "Wed"
            4 -> "Thu"
            5 -> "Fri"
            6 -> "Sat"
            7 -> "Sun"
            else -> "lol"
        }
    }

    override fun compareDateWithCurrent(localDate: LocalDate, kind: Int): Boolean {
        val current = getCurrentDate().toEpochDay()
        val actuallyDate = localDate.toEpochDay()

        return when(kind){
            Constants.MoreThenCurrentDate -> actuallyDate > current
            Constants.SmallThenCurrentDate -> actuallyDate < current
            else -> actuallyDate == current
        }

    }

    override fun checkDateInRange(firstDate: String, secondDate: String, checkableDate:String): Boolean {
        val date1 = firstDate.toLocalDate().toEpochDay()
        val date2 = secondDate.toLocalDate().toEpochDay()
        val currentDate = checkableDate.toLocalDate().toEpochDay()
        return (date1 <= currentDate) && (currentDate <= date2)
    }

    override fun getDatesInRange(firstDate: String, secondDate: String): List<String> {
        val date1 = firstDate.toLocalDate().minusDays(1)
        var date2 = secondDate.toLocalDate()
        Log.d("dates_first","first: $firstDate  second:$secondDate")
        val output = mutableListOf<String>()

        while (date1 != date2){
            output.add(date2.toDateString())
            date2 = date2.minusDays(1)
        }

        return output.reversed()
    }

    override fun convertUriToBitmap(uri: Uri): Bitmap {
        return imageStorage.convertUriToBitmap(uri)
    }

    override fun convertBitmapToFile(bitmap: Bitmap): File {
        return imageStorage.convertBitmapToFile(bitmap)
    }

    override fun getTypeOfImage(uri: Uri): String {
        return imageStorage.getTypeOfImage(uri)
    }

    override suspend fun saveImage(uri: Uri, id:Int) {
        imageStorage.saveImage(uri,id)
    }

    override fun getImageFileById(id: Int): File {
        return imageStorage.getImageFileById(id)
    }

    override fun convertFileToByteArray(file: File): ByteArray {
        return file.readBytes()
    }

    override fun deleteFile(file: File) {
        imageStorage.deleteFile(file)
    }

    override fun saveByteArrayAsFile(id: Int, byteArray: ByteArray) {
        imageStorage.saveByteArrayAsFile(id, byteArray)
    }

    override fun putActuallyMainTaskId(id: Int) {
        idsStorage.putActuallyMainTaskId(id)
    }

    override fun getActuallyMainTaskId(): Int {
        return idsStorage.getActuallyMainTaskId()
    }

    override fun putActuallySubtaskId(id: Int) {
        idsStorage.putActuallySubtaskId(id)
    }

    override fun getActuallySubtaskId(): Int {
        return idsStorage.getActuallySubtaskId()
    }

    override fun putActuallyStatisticsId(id: Int) {
        idsStorage.putActuallyStatisticsId(id)
    }

    override fun getActuallyStatisticsId(): Int {
        return idsStorage.getActuallyStatisticsId()
    }

    override fun putActuallyLongTaskId(id: Int) {
        idsStorage.putActuallyLongTaskId(id)
    }

    override fun getActuallyLongTaskId(): Int {
        return idsStorage.getActuallyLongTaskId()
    }

    override fun saveAppTheme(theme: String) {
        themeStorage.saveAppTheme(theme)
    }

    override fun getAppTheme(): String {
        return themeStorage.getAppTheme()
    }

}

fun String.toLocalDate():LocalDate{
    val dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    return LocalDate.parse(this, dtf)
}


fun LocalDate.toDateString():String{

    val day = this.dayOfMonth
    val month = this.monthValue
    val year = this.year

    return "${if(day < 10) "0$day" else day}-${if(month < 10) "0$month" else month}-$year"
}

fun Date.toLocalDate(): LocalDate {
    return toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}