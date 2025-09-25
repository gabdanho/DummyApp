package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.PostComments
import com.example.authapp.domain.model.user.PostComments as PostCommentsDomain

/**
 * Преобразует [PostCommentsDomain] в [PostComments].
 */
fun PostCommentsDomain.toPresentationLayer(): PostComments {
    return PostComments(
        comments = comments.map { it.toPresentationLayer() },
        total = total
    )
}

/**
 * Преобразует [PostComments] в [PostCommentsDomain].
 */
fun PostComments.toDomainLayer(): PostCommentsDomain {
    return PostCommentsDomain(
        comments = comments.map { it.toDomainLayer() },
        total = total
    )
}