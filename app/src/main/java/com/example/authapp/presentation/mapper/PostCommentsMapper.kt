package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.PostComments
import com.example.authapp.domain.model.user.PostComments as PostCommentsDomain

fun PostCommentsDomain.toPresentationLayer(): PostComments {
    return PostComments(
        comments = comments.map { it.toPresentationLayer() },
        total = total
    )
}

fun PostComments.toDomainLayer(): PostCommentsDomain {
    return PostCommentsDomain(
        comments = comments.map { it.toDomainLayer() },
        total = total
    )
}