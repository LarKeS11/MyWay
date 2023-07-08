package com.example.mywaycompose.data.local.local_storage.theme_storage

import android.content.Context
import com.example.mywaycompose.utils.Constants
import javax.inject.Inject

class ThemeStorage @Inject constructor(
    private val context: Context
) {
    private val sharedPreferences = context.getSharedPreferences("app_theme", Context.MODE_PRIVATE)

     fun saveAppTheme(theme: String) {
        sharedPreferences.edit().putString("app_theme",theme).apply()
    }

     fun getAppTheme(): String {
        return sharedPreferences.getString("app_theme",Constants.DAY_MAIN_THEME)!!
    }
}