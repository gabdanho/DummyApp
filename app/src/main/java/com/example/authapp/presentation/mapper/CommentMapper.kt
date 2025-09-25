package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.Comment
import com.example.authapp.domain.model.user.Comment as CommentDomain

/**
 * Преобразует [CommentDomain] в [Comment].
 */
fun CommentDomain.toPresentationLayer(): Comment {
    return Comment(
        id = id,
        body = body,
        user = user.toPresentationLayer()
    )
}

/**
 * Преобразует [Comment] в [CommentDomain].
 */
fun Comment.toDomainLayer(): CommentDomain {
    return CommentDomain(
        id = id,
        body = body,
        user = user.toDomainLayer()
    )
}