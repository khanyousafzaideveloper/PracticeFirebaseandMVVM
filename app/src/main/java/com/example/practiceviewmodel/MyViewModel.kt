package com.example.practiceviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult){
        _state.update { it.copy(
            isSignInSuccessful = result?.data != null,
            signInError = result.errorMessage
        )
        }
    }

    fun resetState(){
        _state.update { SignInState() }
    }













    var name by mutableStateOf("")
    var counter by mutableStateOf(0)
        private set

    fun setCounterValue(value: Int) {
        counter = value
    }
    fun getCounterValue(): Int {
        return counter
    }

    fun incrementCounter() {
        counter++
    }
}