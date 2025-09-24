package com.example.authapp.presentation.navigation.model

import kotlinx.serialization.Serializable

/**
 * Состояния навигации приложения.
 */
@Serializable
sealed class AppGraph : NavigationDestination {

    @Serializable
    data object AuthScreen : AppGraph()

    @Serializable
    data object MainScreen : AppGraph()

    @Serializable
    data class PostScreen(val id: Int? = null) : AppGraph()

    @Serializable
    data class UserPostsScreen(val id: Int? = null) : AppGraph()

    @Serializable
    data object SearchPerson : AppGraph()

    @Serializable
    data class UserDetailsScreen(val id: Int? = null) : AppGraph()
}