package com.example.mywaycompose.presentation.ui.screen.auth_screen.state

data class LastStepAuthState(
    val isLoading:Boolean = false,
    val isSuccess:Boolean = false,
    val error:String = ""
)