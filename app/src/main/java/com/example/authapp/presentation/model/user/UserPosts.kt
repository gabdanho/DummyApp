package com.example.authapp.presentation.model.user

/**
 * Посты конкретного пользователя.
 *
 * @property posts список [Post].
 */
data class UserPosts(
    val posts: List<Post> = emptyList(),
)