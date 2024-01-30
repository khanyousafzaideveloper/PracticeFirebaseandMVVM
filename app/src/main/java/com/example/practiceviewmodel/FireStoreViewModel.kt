package com.example.practiceviewmodel

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireStoreViewModel: ViewModel() {
    var name by mutableStateOf("")

    var university by mutableStateOf("")

    var rollNo by mutableStateOf("")

    var section by mutableStateOf("")

    var dName by mutableStateOf("")

    var dUniversity by mutableStateOf("")

    var dRollNo by mutableStateOf("")

    var dSection by mutableStateOf("")

    var firestore = Firebase.firestore


    fun onClick(){
        val userMap = hashMapOf(
            "name" to name,
            "university" to university,
            "rollNo" to rollNo,
            "section" to section
        )
        firestore.collection("users")
            .add(userMap)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
        name = ""
        university = ""
        rollNo = ""
        section = ""


        firestore.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    dName = document.get("name").toString()
                    dUniversity = document.get("university").toString()
                    dSection = document.get("section").toString()
                    dRollNo = document.get("rollNo").toString()
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}