package com.example.authapp.presentation.model.user

data class PostComments(
    val comments: List<Comment> = emptyList(),
    val total: Int = 0,
)