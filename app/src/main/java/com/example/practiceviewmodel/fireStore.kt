package com.example.practiceviewmodel

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FireStore(viewModel:FireStoreViewModel = viewModel()) {


    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name")
        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.Words
            )
        )
        Text(text = "University")
        OutlinedTextField(
            value = viewModel.university,
            onValueChange = { viewModel.university = it }, keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.Words
            )
        )
        Text(text = "Roll No")
        OutlinedTextField(
            value = viewModel.rollNo,
            onValueChange = { viewModel.rollNo = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Text(text = "Section")
        OutlinedTextField(
            value = viewModel.section,
            onValueChange = { viewModel.section = it },
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.Characters
            )
        )

        Button(onClick = { viewModel.onClickBtn() }) {
            Text(text = "Push to Firestore")
        }
        var retrievedText by remember { mutableStateOf("No data retrieved") }
        Button(onClick = { viewModel.Practice()}){
            Text(text = "Retrieve")
        }

            Text(text = retrievedText)

        
        
    }
}

