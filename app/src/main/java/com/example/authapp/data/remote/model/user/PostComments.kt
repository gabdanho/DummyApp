package com.example.authapp.data.remote.model.user

data class PostComments(
    val comments: List<Comment>,
    val total: Int
)