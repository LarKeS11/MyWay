package com.example.mywaycompose.data.repository.service

import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.utils.Constants.START_DAY_USING_KIND
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow

class DataRemoteServiceRepository(
    private val firebaseService: FirebaseService
):RemoteServiceRepository {


    override fun googleAuthRequest(): GoogleSignInClient {
        return firebaseService.googleAuthRequest()
    }

    override fun firebaseAuthWithGoogle(idToken: String): Task<AuthResult> {
        return firebaseService.firebaseAuthWithGoogle(idToken)
    }

    override fun getUserAuth(): FirebaseAuth {
        return firebaseService.getUserAuth()
    }

    override fun getTasksDatabaseReference(): DatabaseReference {
        return firebaseService.getTasksDatabaseReference()
    }


    override fun putActuallyMainTaskId(id: Int) {
        firebaseService.putActuallyMainTaskId(id)
    }

    override fun putActuallySubtaskId(id:Int) {
        firebaseService.putActuallySubtaskId(id)
    }

    override fun putActuallyStatisticsId(id: Int) {
        firebaseService.putActuallyStatisticsId(id)
    }

    override fun putActuallyLongTaskId(id: Int) {
        firebaseService.putActuallyLongTaskId(id)
    }

    override fun getIdsDatabaseReference(): DatabaseReference {
        return firebaseService.getIdsDatabaseReference()
    }

    override fun getSubtasksDatabaseRef(): DatabaseReference {
        return firebaseService.getSubtasksDatabaseRef()
    }
    override fun deletePhotoById(id: Int) {
        firebaseService.deletePhotoById(id)
    }

    override fun getTasksStatisticsDatabaseReference(): DatabaseReference {
        return firebaseService.getTasksStatisticsDatabaseReference()
    }

    override fun getMainTasksDatabaseRef(): DatabaseReference {
        return firebaseService.getMainTasksDatabaseRef()
    }
    override fun getImageReference(id: Int):StorageReference {
        return firebaseService.getImageReference(id)
    }

    override fun getCurrentIdBySomeModel(kind: String): Flow<Int> {
        return firebaseService.getCurrentIdBySomeModel(kind)
    }

    override fun <T> getAppData(kind: String): Flow<List<T>> {
        return firebaseService.getAppData(kind)
    }

    override fun putStartUsingDate(date: String) {
        firebaseService.putFirstDate(date)
    }

    override fun getStartUsingDate(): Flow<List<String>> {
        return firebaseService.getAppData(START_DAY_USING_KIND)
    }
}