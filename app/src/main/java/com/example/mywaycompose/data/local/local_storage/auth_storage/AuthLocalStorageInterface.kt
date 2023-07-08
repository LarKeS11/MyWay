package com.example.mywaycompose.data.local.local_storage.auth_storage

interface AuthLocalStorageInterface {
    fun putSession(session:String)
    fun getSession():String
    fun saveFirstDate(date:String)
    fun getFirstDate():String
}