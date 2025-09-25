package com.example.authapp.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.authapp.presentation.model.user.UserLogin
import com.example.authapp.presentation.navigation.model.AppGraph
import com.example.authapp.presentation.navigation.model.nav_type.UserLoginNavType
import com.example.authapp.presentation.screens.auth.AuthScreen
import com.example.authapp.presentation.screens.main.MainScreen
import com.example.authapp.presentation.screens.search.SearchPersonScreen
import com.example.authapp.presentation.screens.post.SelectedPostScreen
import com.example.authapp.presentation.screens.user.UserDetailsScreen
import com.example.authapp.presentation.screens.posts.UserPostsScreen
import kotlin.reflect.typeOf

/**
 * Расширение для [NavGraphBuilder] для построения графа навигации приложения.
 *
 * @param modifier [Modifier] для передачи в экраны.
 */
fun NavGraphBuilder.appGraph(
    modifier: Modifier = Modifier,
) {
    composable<AppGraph.AuthScreen> {
        AuthScreen()
    }

    composable<AppGraph.MainScreen>(
        typeMap = mapOf(typeOf<UserLogin>() to UserLoginNavType())
    ) {
        val args = it.toRoute<AppGraph.MainScreen>()
        MainScreen(userLogin = args.userLogin)
    }

    composable<AppGraph.SearchPerson> {
        SearchPersonScreen()
    }

    composable<AppGraph.PostScreen> { it ->
        val args = it.toRoute<AppGraph.PostScreen>()

        args.id?.let { id ->
            SelectedPostScreen(id = id)
        }
    }

    composable<AppGraph.UserPostsScreen> {
        val args = it.toRoute<AppGraph.PostScreen>()

        args.id?.let { id ->
            UserPostsScreen(id = id)
        }
    }

    composable<AppGraph.UserDetailsScreen> {
        val args = it.toRoute<AppGraph.PostScreen>()

        args.id?.let { id ->
            UserDetailsScreen(id = id)
        }
    }
}