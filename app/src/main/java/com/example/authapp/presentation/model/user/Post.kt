package com.example.authapp.presentation.model.user

/**
 * Пост.
 *
 * @property id идентификатор поста.
 * @property title заголовок поста.
 * @property body содержимое поста.
 * @property userId идентификатор пользователя.
 * @property tags список тегов.
 */
data class Post(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val userId: Int = 0,
    val tags: List<String> = emptyList(),
)