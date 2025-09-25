package com.example.authapp.presentation.navigation.model

import com.example.authapp.presentation.model.user.UserLogin
import kotlinx.serialization.Serializable

/**
 * Состояния навигации приложения.
 */
@Serializable
sealed class AppGraph : NavigationDestination {

    @Serializable
    data object AuthScreen : AppGraph()

    @Serializable
    data class MainScreen(val userLogin: UserLogin = UserLogin()) : AppGraph()

    @Serializable
    data class PostScreen(val id: Int? = null) : AppGraph()

    @Serializable
    data class UserPostsScreen(val id: Int? = null) : AppGraph()

    @Serializable
    data class SearchPerson(val authUserId: Int? = null) : AppGraph()

    @Serializable
    data class UserDetailsScreen(val id: Int? = null, val isAnotherUser: Boolean = false) : AppGraph()
}