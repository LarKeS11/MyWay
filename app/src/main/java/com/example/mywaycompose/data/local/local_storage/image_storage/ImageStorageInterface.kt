package com.example.mywaycompose.data.local.local_storage.image_storage

import android.graphics.Bitmap
import android.net.Uri
import java.io.File

interface ImageStorageInterface {

    fun convertUriToBitmap(uri: Uri):Bitmap
    fun convertBitmapToFile(bitmap: Bitmap): File
    fun getTypeOfImage(uri:Uri):String
    suspend fun saveImage(uri:Uri, id:Int)
    fun getImageId():Int
    fun getImageFileById(id:Int):File
    fun deleteFile(file:File)
    fun convertByteArrayToBitmap(byteArray: ByteArray):Bitmap
    fun saveByteArrayAsFile(id:Int, byteArray: ByteArray)

}