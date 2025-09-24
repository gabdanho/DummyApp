package com.example.authapp.presentation.model.user

data class UserList(
    val users: List<User> = emptyList(),
    val total: Int = 0,
)