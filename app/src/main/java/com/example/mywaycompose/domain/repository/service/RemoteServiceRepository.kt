package com.example.mywaycompose.domain.repository.service

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow

interface RemoteServiceRepository {
    fun googleAuthRequest(): GoogleSignInClient
    fun firebaseAuthWithGoogle(idToken:String):com.google.android.gms.tasks.Task<AuthResult>
    fun getUserAuth(): FirebaseAuth
    fun getTasksDatabaseReference(): DatabaseReference
    fun putActuallyMainTaskId(id:Int)
    fun putActuallySubtaskId(id:Int)
    fun putActuallyStatisticsId(id:Int)
    fun putActuallyLongTaskId(id:Int)
    fun getIdsDatabaseReference():DatabaseReference
    fun getSubtasksDatabaseRef():DatabaseReference
    fun deletePhotoById(id:Int)
    fun getTasksStatisticsDatabaseReference():DatabaseReference
    fun getMainTasksDatabaseRef():DatabaseReference
    fun getImageReference(id:Int): StorageReference
    fun getCurrentIdBySomeModel(kind:String): Flow<Int>
    fun <T> getAppData(kind:String):Flow<List<T>>

    fun putStartUsingDate(date:String)
    fun getStartUsingDate():Flow<List<String>>
}