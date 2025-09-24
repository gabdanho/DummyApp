package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.Post
import com.example.authapp.domain.model.user.Post as PostDomain

fun PostDomain.toDataLayer(): Post {
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