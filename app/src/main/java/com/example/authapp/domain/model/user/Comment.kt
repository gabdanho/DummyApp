package com.example.authapp.domain.model.user

data class Comment(
    val id: Int = 0,
    val body: String = "",
    val user: User = User(),
)