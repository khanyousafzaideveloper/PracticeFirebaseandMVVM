package com.example.practiceviewmodel

data class SignInResult(
    val data: com.example.practiceviewmodel.UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profielPictureUrl: String?
)
