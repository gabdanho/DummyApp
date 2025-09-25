package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.Post
import com.example.authapp.domain.model.user.Post as PostDomain

/**
 * Преобразует [PostDomain] в [Post].
 */
fun PostDomain.toPresentationLayer(): Post {
    return Post(
        id = id,
        title = title,
        body = body,
        userId = userId,
        tags = tags
    )
}

/**
 * Преобразует [Post] в [PostDomain].
 */
fun Post.toDomainLayer(): PostDomain {
    return PostDomain(
        id = id,
        title = title,
        body = body,
        userId = userId,
        tags = tags
    )
}