package com.example.mywaycompose.presentation.ui.screen.auth_screen.state

import com.google.android.gms.auth.api.signin.GoogleSignInClient

data class AuthorizationUserState(
    val signInClient:GoogleSignInClient? = null,
    val error:String = "",
    val isLoading:Boolean = false
)