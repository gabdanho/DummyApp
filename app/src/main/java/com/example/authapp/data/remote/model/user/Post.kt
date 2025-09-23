package com.example.authapp.data.remote.model.user

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    val tags: List<String>
)