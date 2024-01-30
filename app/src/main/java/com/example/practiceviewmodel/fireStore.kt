package com.example.practiceviewmodel

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FireStore( ) {


    var name by remember {
        mutableStateOf("")
    }

    var university by  remember {
        mutableStateOf("")
    }

    var rollNo by remember {
        mutableStateOf("")
    }

    var section by remember {
        mutableStateOf("")
    }

    val firestore = Firebase.firestore

    val userMap = hashMapOf(
        "name" to name,
        "university" to university,
        "rollNo" to rollNo,
        "section" to section
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name")
        OutlinedTextField(value = name, onValueChange = { name = it })
        Text(text = "University")
        OutlinedTextField(
            value = university,
            onValueChange = { university = it })
        Text(text = "Roll No")
        OutlinedTextField(value = rollNo, onValueChange = { rollNo = it })
        Text(text = "Section")
        OutlinedTextField(value = section, onValueChange = { section = it })

        Button(onClick = { firestore.collection("users")
            .add(userMap)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            } }) {
            Text(text = "Push to Firestore")
        }
    }
}