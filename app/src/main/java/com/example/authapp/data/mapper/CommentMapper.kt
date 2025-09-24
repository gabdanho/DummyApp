package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.Comment
import com.example.authapp.domain.model.user.Comment as CommentDomain

fun CommentDomain.toDataLayer(): Comment {
    return Comment(
        id = id,
        body = body,
        user = user.toDataLayer()
    )
}

fun Comment.toDomainLayer(): CommentDomain {
    return CommentDomain(
        id = id,
        body = body,
        user = user.toDomainLayer()
    )
}