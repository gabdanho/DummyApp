package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.UserPosts
import com.example.authapp.domain.model.user.UserPosts as UserPostsDomain

fun UserPostsDomain.toPresentationLayer(): UserPosts {
    return UserPosts(
        posts = posts.map { it.toPresentationLayer() }
    )
}

fun UserPosts.toDomainLayer(): UserPostsDomain {
    return UserPostsDomain(
        posts = posts.map { it.toDomainLayer() }
    )
}