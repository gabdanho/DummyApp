package com.example.authapp.domain.model.user

data class User(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val age: String = "",
    val gender: String = "",
    val birthDate: String = "",
    val image: String = "",
    val university: String = "",
    val department: String = "",
)