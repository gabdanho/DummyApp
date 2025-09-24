package com.example.authapp.data.remote.model.user

data class UserLogin(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val image: String = "",
    val token: String = ""
)