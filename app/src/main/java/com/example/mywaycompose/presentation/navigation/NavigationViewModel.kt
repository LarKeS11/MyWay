package com.example.mywaycompose.presentation.navigation

import androidx.lifecycle.ViewModel
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.utils.Constants.DAY_MAIN_THEME
import com.example.mywaycompose.utils.Constants.DAY_MAIN_THEME_COLORS
import com.example.mywaycompose.utils.Constants.NIGHT_MAIN_THEME
import com.example.mywaycompose.utils.Constants.NIGHT_MAIN_THEME_COLORS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(

):ViewModel() {

    private val _currentMainThemeColors = MutableStateFlow(DAY_MAIN_THEME_COLORS)
    val currentMainThemeColors = _currentMainThemeColors

    fun switchMainThemeColors(){
        if(_currentMainThemeColors.value.type == "day") _currentMainThemeColors.value = DAY_MAIN_THEME_COLORS
        else _currentMainThemeColors.value = NIGHT_MAIN_THEME_COLORS
    }

}