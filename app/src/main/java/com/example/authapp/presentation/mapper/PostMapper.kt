package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.Post
import com.example.authapp.domain.model.user.Post as PostDomain

fun PostDomain.toPresentationLayer(): Post {
    return Post(
        id = id,
        title = title,
        body = body,
        userId = userId,
        tags = tags
    )
}

fun Post.toDomainLayer(): PostDomain {
    return PostDomain(
        id = id,
        title = title,
        body = body,
        userId = userId,
        tags = tags
    )
}