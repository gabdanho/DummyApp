package com.example.authapp.data.remote.model.user

/**
 * Посты конкретного пользователя.
 *
 * @property posts список [Post].
 */
data class UserPosts(
    val posts: List<Post> = emptyList(),
)