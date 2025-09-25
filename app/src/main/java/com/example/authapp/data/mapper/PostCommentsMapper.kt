package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.PostComments
import com.example.authapp.domain.model.user.PostComments as PostCommentsDomain

/**
 * Преобразует [PostCommentsDomain] в [PostComments].
 */
fun PostCommentsDomain.toDataLayer(): PostComments {
    return PostComments(
        comments = comments.map { it.toDataLayer() },
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