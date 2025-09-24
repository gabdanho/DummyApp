package com.example.authapp.domain.model.user

data class UserList(
    val users: List<User> = emptyList(),
    val total: Int = 0,
)