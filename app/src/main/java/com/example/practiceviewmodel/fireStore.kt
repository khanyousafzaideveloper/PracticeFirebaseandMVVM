package com.example.practiceviewmodel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import androidx.compose.runtime.saveable.rememberSaveable as rememberSaveable1


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FireStore(viewModel:FireStoreViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {


    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name")
        OutlinedTextField(value = viewModel.name, onValueChange = { viewModel.name = it })
        Text(text = "University")
        OutlinedTextField(value = viewModel.university, onValueChange = { viewModel.university = it })
        Text(text = "Roll No")
        OutlinedTextField(value = viewModel.rollNo, onValueChange = { viewModel.rollNo = it })
        Text(text = "Section")
        OutlinedTextField(value = viewModel.section, onValueChange = { viewModel.section = it })

        Button(onClick = { viewModel.onClick() }) {
            Text(text = "Push to Firestore")
        }
    }



}