package com.example.authapp.data.remote.model.user

data class Comment(
    val id: Int = 0,
    val body: String = "",
    val user: User = User(),
)