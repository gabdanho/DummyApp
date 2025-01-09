package com.example.authapp.model

data class PostComments(
    val comments: List<Comment>,
    val total: Int
)

data class Comment(
    val id: Int,
    val body: String,
    val user: User
)