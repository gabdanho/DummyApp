package com.example.authapp.domain.model.user

data class UserList(
    val users: List<User>,
    val total: Int
)