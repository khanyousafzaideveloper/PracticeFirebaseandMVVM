package com.example.practiceviewmodel

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch


@Composable
    fun Hello(navController:NavHostController, lifecycle: LifecycleCoroutineScope) {
        val applicationContext = LocalContext.current
        val googleAuthUiClient by lazy {
            GoogleAuthUiClient(
                context = applicationContext,
                oneTapClient = com.google.android.gms.auth.api.identity.Identity.getSignInClient(applicationContext)
            )
        }



        NavHost(navController = navController, startDestination = "sign_in") {
            composable("sign_in") {
                val viewModel = viewModel<HomeViewModel>()
                val state by viewModel.state.collectAsState()   /////

                LaunchedEffect(key1 = Unit) {
                    if (googleAuthUiClient.signedInUser() != null) {
                        navController.navigate("profile")
                    }
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        if (result.resultCode == ComponentActivity.RESULT_OK) {
                            lifecycle.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                viewModel.onSignInResult(signInResult)
                            }
                        }

                    }
                )
                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if (state.isSignInSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Sign in Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate("profile")
                        viewModel.resetState()
                    }
                }

                SignInScreen(
                    state = state,
                    onSignInClickL = {
                        lifecycle.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
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
            composable("profile") {
                ProfileScreen(userData = googleAuthUiClient.signedInUser(), onSignOut = {
                    lifecycle.launch {
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

