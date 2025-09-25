package com.example.authapp.domain.model.user

/**
 * Комментарий к посту.
 *
 * @property id идентификатор комментария.
 * @property body текст комментария.
 * @property user [User] автор комментария.
 */
data class Comment(
    val id: Int = 0,
    val body: String = "",
    val user: User = User(),
)