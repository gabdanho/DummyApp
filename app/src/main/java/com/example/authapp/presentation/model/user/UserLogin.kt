package com.example.authapp.presentation.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val image: String = "",
    val token: String = "",
)