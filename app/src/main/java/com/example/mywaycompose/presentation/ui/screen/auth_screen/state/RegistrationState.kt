package com.example.mywaycompose.presentation.ui.screen.auth_screen.state


data class RegistrationState(
    val isSessionLoading:Boolean = false,
    val session:String = "",
    val error:String = "",
    val isImageLoading:Boolean = false,
    val imageError:String = ""
)