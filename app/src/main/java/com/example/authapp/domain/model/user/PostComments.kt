package com.example.authapp.domain.model.user

data class PostComments(
    val comments: List<Comment>,
    val total: Int
)