package com.example.mywaycompose.data.local.local_storage.auth_storage

import android.content.Context
import android.util.Log

class AuthLocalStorage(
    private val context: Context
    ): AuthLocalStorageInterface {

    private val sharedPreferences =
        context.getSharedPreferences("AuthData", Context.MODE_PRIVATE)

    override fun putSession(session:String){
        sharedPreferences.edit().putString("session", session).apply()
    }

    override fun getSession():String{
        return sharedPreferences.getString("session", "none")!!
    }

    override fun saveFirstDate(date: String) {
        Log.d("dfsdf",date)
        sharedPreferences.edit().putString("date",date).apply()
    }

    override fun getFirstDate(): String {
        return sharedPreferences.getString("date","none")!!
    }

}