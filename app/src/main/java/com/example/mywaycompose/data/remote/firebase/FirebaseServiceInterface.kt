package com.example.mywaycompose.data.remote.firebase

import com.example.mywaycompose.data.repository.firebase_models.*
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.utils.Resource
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow

interface FirebaseServiceInterface {

    fun googleAuthRequest(): GoogleSignInClient
    fun firebaseAuthWithGoogle(idToken:String):com.google.android.gms.tasks.Task<AuthResult>
    fun getUserAuth(): FirebaseAuth
    fun getTasksDatabaseReference():DatabaseReference
    fun firstUserMarkup()
    fun pushTaskToDatabase(task: FirebaseTask)
    fun pushMainTaskToDatabase(mainTask: FirebaseMainTask)
    fun pushMainTaskImageFirebase(image:ByteArray, id:Int)
    fun pushLongTaskStatToDatabase(longTask: FirebaseLongTaskStat)
    fun getMainTasksDatabaseRef():DatabaseReference
    fun getImageReference(id:Int): StorageReference
    fun deleteTask(task:FirebaseTask)
    fun deleteMainTask(mainTask: FirebaseMainTask)
    fun putActuallyMainTaskId(id:Int)
    fun putActuallySubtaskId(id:Int)
    fun putActuallyStatisticsId(id:Int)
    fun putActuallyLongTaskId(id:Int)
    fun getIdsDatabaseReference():DatabaseReference
    fun pushSubtaskToDatabase(subTask: FirebaseSubtask)
    fun getSubtasksDatabaseRef():DatabaseReference
    fun deletePhotoById(id:Int)
    fun cleanSubtasksByMainTaskId(id:Int)
    fun pushTaskStatistic(taskStatistic: FirebaseTaskStat)
    fun getTasksStatisticsDatabaseReference():DatabaseReference
    fun putCurrentEffectiveValue(value:Int)
    fun putCurrentDayEffective(date:String, value:Int)
    fun getEffectiveStatisticsByDay(date:String): Flow<Int>
    fun pushIdea(idea: FirebaseIdea)
    fun deleteIdea(idea: FirebaseIdea)

    fun getCurrentIdBySomeModel(kind:String):Flow<Int>

    fun addLongTask(firebaseLongTask: FirebaseLongTask)
    fun deleteLongTask(firebaseLongTask: FirebaseLongTask)

    fun <T> getAppData(kind:String): Flow<List<T>>

    fun putFirstDate(date:String)

}