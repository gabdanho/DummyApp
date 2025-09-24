package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.Comment
import com.example.authapp.domain.model.user.Comment as CommentDomain

fun CommentDomain.toPresentationLayer(): Comment {
    return Comment(
        id = id,
        body = body,
        user = user.toPresentationLayer()
    )
}

fun Comment.toDomainLayer(): CommentDomain {
    return CommentDomain(
        id = id,
        body = body,
        user = user.toDomainLayer()
    )
}