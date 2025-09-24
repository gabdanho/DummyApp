package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.AuthRequest
import com.example.authapp.domain.model.user.AuthRequest as AuthRequestDomain

fun AuthRequestDomain.toPresentationLayer(): AuthRequest {
    return AuthRequest(
        username = username,
        password = password
    )
}

fun AuthRequest.toDomainLayer(): AuthRequestDomain {
    return AuthRequestDomain(
        username = username,
        password = password
    )
}