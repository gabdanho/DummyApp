package com.example.authapp.presentation.navigation.model.nav_type

import com.example.authapp.presentation.model.user.UserLogin
import kotlinx.serialization.KSerializer

/**
 * [NavType] для передачи объектов [UserLogin] между экранами.
 */
class UserLoginNavType(serializer: KSerializer<UserLogin> = UserLogin.serializer()) :
    NavTypeSerializer<UserLogin>(
        serializer = serializer
    )