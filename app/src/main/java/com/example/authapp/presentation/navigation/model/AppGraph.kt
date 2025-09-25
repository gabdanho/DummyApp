package com.example.authapp.presentation.navigation.model

import com.example.authapp.presentation.model.user.UserLogin
import kotlinx.serialization.Serializable

/**
 * Состояния навигации приложения.
 */
@Serializable
sealed class AppGraph : NavigationDestination {

    /** Экран авторизации */
    @Serializable
    data object AuthScreen : AppGraph()

    /** Главный экран, принимает [UserLogin] */
    @Serializable
    data class MainScreen(val userLogin: UserLogin = UserLogin()) : AppGraph()

    /** Экран просмотра поста */
    @Serializable
    data class PostScreen(val id: Int? = null) : AppGraph()

    /** Экран со списком постов пользователя */
    @Serializable
    data class UserPostsScreen(val id: Int? = null) : AppGraph()

    /** Экран поиска пользователей */
    @Serializable
    data class SearchPerson(val authUserId: Int? = null) : AppGraph()

    /** Экран деталей пользователя */
    @Serializable
    data class UserDetailsScreen(val id: Int? = null, val isAnotherUser: Boolean = false) : AppGraph()
}