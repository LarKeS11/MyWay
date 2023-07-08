package com.example.mywaycompose.presentation.ui.screen.auth_screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywaycompose.R
import com.example.mywaycompose.presentation.navigation.Screen
import com.example.mywaycompose.presentation.ui.screen.auth_screen.component.*
import com.example.mywaycompose.presentation.ui.screen.auth_screen.state.AuthorizationUserState
import com.google.android.gms.auth.api.signin.GoogleSignIn

@Composable
fun AuthorizationScreen(
    navController: NavController,
    viewModel: AuthorizationViewModel = hiltViewModel()
) {

    var signInClient:AuthorizationUserState? = viewModel.authState.value
    val lastStep = viewModel.lastStepAuthState.value
    val navState = remember {
        mutableStateOf(true)
    }

    val auth = viewModel._authSession.value

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        val account = task.result
        signInClient = null
        if(account != null) {
            viewModel.pushAuthToFirebase(account.idToken!!)
        }
    }

    if((auth.currentUser != null || lastStep.isSuccess) && navState.value){
        navController.navigate(Screen.SplashScreen.route)
        navState.value = false
    }



    if(
       signInClient!!.signInClient != null
       && !lastStep.isLoading
       && !lastStep.isSuccess
       && lastStep.error.isEmpty()
    ){
        launcher.launch(signInClient!!.signInClient!!.signInIntent)
    }


    Box(modifier = Modifier.fillMaxSize()) {
        AuthBackgroundComponent()
        Column(
            modifier = Modifier
                .padding(bottom = 150.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
            ,
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTitleComponent(title = "Sign in with:")
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                          viewModel.getSignInClient()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iconsgoogle),
                    contentDescription = "",
                    modifier = Modifier.size(43.dp)
                )
            }
        }
    }
}