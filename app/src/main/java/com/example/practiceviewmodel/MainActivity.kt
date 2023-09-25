package com.example.practiceviewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practiceviewmodel.ui.theme.PracticeViewModelTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = com.google.android.gms.auth.api.identity.Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController= rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in" ){
                        composable("sign_in"){
                            val viewModel = viewModel<HomeViewModel>()
                            val state by viewModel.state.collectAsState()   /////

                            val launcher= rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if(result.resultCode == RESULT_OK){
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }

                                }
                            )
                            LaunchedEffect(key1 = state.isSignInSuccessful){
                                if(state.isSignInSuccessful){
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in Successful",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onSignInClickL = {
                                    lifecycleScope.launch {
                                        val signInIntentSender= googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            ) {

                            }
                        }
                        composable("profile" ){
                            profileScreen(userData = googleAuthUiClient.signedInUser(), onSignOut = {lifecycleScope.launch {
                                googleAuthUiClient.signout()
                                Toast.makeText(
                                    applicationContext,
                                    "Signed Out",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                            }
                            })
                        }
                    }
                }
            }
        }
    }
}
