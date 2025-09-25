package com.example.authapp.data.remote.model.user

/**
 * Список комментариев к посту.
 *
 * @property comments список [Comment].
 * @property total количество комментариев.
 */
data class PostComments(
    val comments: List<Comment> = emptyList(),
    val total: Int = 0,
)