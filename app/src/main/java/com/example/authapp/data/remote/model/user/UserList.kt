package com.example.authapp.data.remote.model.user

data class UserList(
    val users: List<User> = emptyList(),
    val total: Int = 0,
)