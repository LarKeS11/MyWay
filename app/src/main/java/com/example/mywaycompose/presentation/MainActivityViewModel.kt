package com.example.mywaycompose.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mywaycompose.domain.usecase.local_service.UseGetAppTheme
import com.example.mywaycompose.domain.usecase.local_service.UseSaveAppTheme
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.utils.Constants
import com.example.mywaycompose.utils.Constants.DAY_MAIN_THEME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val useGetAppTheme: UseGetAppTheme,
    private val useSaveAppTheme: UseSaveAppTheme
):ViewModel() {

    private val _currentMainThemeColors = MutableStateFlow(Constants.DAY_MAIN_THEME_COLORS)
    val currentMainThemeColors = _currentMainThemeColors

    init {
        switchMainThemeColors(useGetAppTheme.execute())
    }

    fun switchMainThemeColors(theme:String){
        when(theme){
            DAY_MAIN_THEME -> _currentMainThemeColors.value = Constants.DAY_MAIN_THEME_COLORS
            else ->  _currentMainThemeColors.value = Constants.NIGHT_MAIN_THEME_COLORS
        }
        useSaveAppTheme.execute(theme)
    }

}