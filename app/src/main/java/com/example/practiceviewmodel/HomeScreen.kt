package com.example.practiceviewmodel

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen() {
//    val viewModel: HomeViewModel = viewModel()
//    Column (){
//    Row{
//        Button(onClick = {viewModel.setCounterValue(0)}) {
//            Text(text = "reset")
//        }
//        Button(onClick = {viewModel.incrementCounter()}) {
//            Text(text = "Increment Value")
//        }
//
//    }
//
//        Button(onClick =  {viewModel.incrementCounter()}) {
//            Text(text = "Value:  ${viewModel.getCounterValue()}"   )
//    }
//        OutlinedTextField(value = viewModel.name , onValueChange = {viewModel.name=it})
//        Text(text = "Hello ${viewModel.name}")
//        SignInScreen(state = , onSignInClickL = { /*TODO*/ }) {
//
//        }
//    }
//
//}
@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClickL: () -> Unit,
    function: () -> Unit
){
    val context=  LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let{ error ->
        Toast.makeText(
            context,
            error,
            Toast.LENGTH_SHORT
        ).show()
        }
    }
    Column(modifier= Modifier.fillMaxWidth()) {
        Button(onClick =  onSignInClickL ) {
            Text(text = "Sign In")
        }
    }

}



