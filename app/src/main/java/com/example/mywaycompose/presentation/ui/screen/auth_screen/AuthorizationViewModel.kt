package com.example.mywaycompose.presentation.ui.screen.auth_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.usecase.remote_service.UseAuthGoogleWithFirebase
import com.example.mywaycompose.domain.usecase.remote_service.UseGetAuthFirebaseSession
import com.example.mywaycompose.domain.usecase.remote_service.UseGetGoogleSignInSetup
import com.example.mywaycompose.domain.usecase.remote_service.UseGetStartUsingDate
import com.example.mywaycompose.domain.usecase.remote_service.UsePutStartUsingDateToFirebase
import com.example.mywaycompose.presentation.ui.screen.auth_screen.state.AuthorizationUserState
import com.example.mywaycompose.presentation.ui.screen.auth_screen.state.LastStepAuthState
import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val useGetGoogleSignInSetup: UseGetGoogleSignInSetup,
    private val useAuthGoogleWithFirebase: UseAuthGoogleWithFirebase,
    private val useGetAuthFirebaseSession: UseGetAuthFirebaseSession
):ViewModel() {

    private val _authState = mutableStateOf(AuthorizationUserState())
    val authState = _authState

    private val _lastStepAuthState = mutableStateOf(LastStepAuthState())
    val lastStepAuthState = _lastStepAuthState

    var _authSession = mutableStateOf(useGetAuthFirebaseSession.execute())

    fun getSignInClient(){
        useGetGoogleSignInSetup.invoke().onEach { res ->
            when(res){
                is Resource.Loading -> _authState.value = AuthorizationUserState(isLoading = true)
                is Resource.Success -> {
                    _authSession.value = useGetAuthFirebaseSession.execute()
                    _authState.value = AuthorizationUserState(signInClient = res.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun pushAuthToFirebase(idToken:String){
        useAuthGoogleWithFirebase.invoke(idToken).onEach { res ->
            when(res){
                is Resource.Loading -> _lastStepAuthState.value = LastStepAuthState(isLoading = true)
                is Resource.Success -> {
                    res.data!!.addOnCompleteListener{
                        _lastStepAuthState.value = LastStepAuthState(isSuccess = true)
                    }
                }
                is Resource.Error -> _lastStepAuthState.value = LastStepAuthState(error = res.message!!)
            }
        }.launchIn(viewModelScope)
    }



}