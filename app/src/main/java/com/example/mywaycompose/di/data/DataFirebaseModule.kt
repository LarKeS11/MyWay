package com.example.mywaycompose.di.data

import android.content.Context
import android.provider.Settings.Global.getString
import com.example.mywaycompose.R
import com.example.mywaycompose.data.remote.firebase.FirebaseService
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInApi
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataFirebaseModule{

    @Singleton
    @Provides
    fun provideSingInRequest(
        context:Context
    ):GoogleSignInClient{
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    @Singleton
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return Firebase.database("https://myway-f26c7-default-rtdb.firebaseio.com")
    }

    @Provides
    fun provideTasksDatabaseReference(database: FirebaseDatabase): DatabaseReference {
        return database.getReference("eee")
    }

    @Singleton
    @Provides
    fun provideFirebaseStorageReference(): StorageReference {
        return Firebase.storage.reference
    }

    @Provides
    fun provideFirebaseService(
        signInClient: GoogleSignInClient,
        database: FirebaseDatabase,
        firebaseStorageReference: StorageReference
    ): FirebaseService {
        return FirebaseService(
            signInClient = signInClient,
            database = database,
            firebaseStorageReference = firebaseStorageReference
        )
    }



}