package com.example.authapp.data.remote.model.user

data class Comment(
    val id: Int,
    val body: String,
    val user: User
)