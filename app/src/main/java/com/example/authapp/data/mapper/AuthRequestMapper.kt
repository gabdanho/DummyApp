package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.AuthRequest
import com.example.authapp.domain.model.user.AuthRequest as AuthRequestDomain

fun AuthRequestDomain.toDataLayer(): AuthRequest {
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