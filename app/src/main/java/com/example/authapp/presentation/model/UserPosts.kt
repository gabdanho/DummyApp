package com.example.authapp.presentation.model

data class UserPosts(
    val posts: List<Post>
)

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    val tags: List<String>
)