package com.example.authapp.domain.model.user

data class Post(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val userId: Int = 0,
    val tags: List<String> = emptyList(),
)