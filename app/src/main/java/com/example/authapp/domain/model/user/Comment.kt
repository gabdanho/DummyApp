package com.example.authapp.domain.model.user

data class Comment(
    val id: Int,
    val body: String,
    val user: User
)