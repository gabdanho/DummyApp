package com.example.authapp.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.authapp.presentation.model.AuthUiState
import com.example.authapp.presentation.screens.AuthScreen
import com.example.authapp.presentation.screens.AuthViewModel
import com.example.authapp.presentation.screens.AuthorizationViewModel
import com.example.authapp.presentation.screens.MainScreen
import com.example.authapp.presentation.screens.SearchPersonScreen
import com.example.authapp.presentation.screens.SelectedPostScreen
import com.example.authapp.presentation.screens.UserDetailsScreen
import com.example.authapp.presentation.screens.UserPostsScreen

@Composable
fun AuthNavGraph(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel<AuthViewModel>(),
    authorizationViewModel: AuthorizationViewModel = viewModel<AuthorizationViewModel>(),
    navController: NavHostController
) {
    val authorizationUiState = authorizationViewModel.uiState.collectAsState()
    val searchPersonsUiState = authViewModel.searchPersonsUiState

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Start.name,
        modifier = modifier
    ) {
        composable(route = NavigationDestination.Start.name) {
            AuthScreen(
                authUser = authViewModel::authUser,
                passwordVisible = authorizationUiState.value.passwordVisible,
                password = authorizationUiState.value.password,
                username = authorizationUiState.value.username,
                isAuthError = authorizationUiState.value.isAuthError,
                updateAuthError = authorizationViewModel::updateAuthError,
                updateUsername = authorizationViewModel::updateUsername,
                updatePasswordVisible = authorizationViewModel::updatePasswordVisible,
                updatePassword = authorizationViewModel::updatePassword,
                onLoginClick = {
                    navController.navigate(NavigationDestination.MainScreen.name)
                }
            )
        }

        composable(
            route = NavigationDestination.MainScreen.name,
            exitTransition = { ExitTransition.None }
        ) {
            MainScreen(
                authUiState = authViewModel.authUiState,
                onLogoutClick = {
                    authViewModel.resetAuthUser()
                    authorizationViewModel.clearUiState()
                    navController.navigate(NavigationDestination.Start.name)
                },
                onMyDetailsClick = {
                    navController.navigate(
                        NavigationDestination.UserDetails.name
                            + "/${(authViewModel.authUiState as AuthUiState.Success).user.id}"
                            + "/${false}"
                    )
                },
                onMyPostsClick = {
                    navController.navigate(
                        NavigationDestination.Posts.name
                            + "/${(authViewModel.authUiState as AuthUiState.Success).user.id}")
                },
                onFoundPersonClick = {
                    navController.navigate(NavigationDestination.FoundPerson.name)
                }
            )
        }

        composable(
            route = NavigationDestination.UserDetails.name + "/{userId}/{isAnotherUser}",
            exitTransition = {
                fadeOut(animationSpec = tween(100))
            },
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("isAnotherUser") { type = NavType.BoolType }
            )
        ) {  entry ->
            val currentId = entry.arguments?.getInt("userId") ?: 0
            authViewModel.getCurrentUser(currentId)
            UserDetailsScreen(
                currentUserUiState = authViewModel.currentUserUiState,
                onBackButtonClick = {
                    navController.popBackStack()
                },
                onPostsClick = { userId ->
                    navController.navigate(NavigationDestination.Posts.name + "/${userId}")
                },
                isAnotherUser = entry.arguments?.getBoolean("isAnotherUser") ?: false
            )
        }

        composable(
            route = NavigationDestination.Posts.name + "/{userId}",
            exitTransition = {
                fadeOut(animationSpec = tween(100))
            },
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { entry ->
            val currentId = entry.arguments?.getInt("userId") ?: 0
            authViewModel.getUserPosts(currentId)
            UserPostsScreen(
                userPostsUiState = authViewModel.userPostsUiState,
                onBackButtonClick = {
                    navController.popBackStack()
                },
                onPostClick = { postId ->
                    navController.navigate(NavigationDestination.SelectedPost.name + "/${postId}")
                }
            )
        }

        composable(
            route = NavigationDestination.SelectedPost.name + "/{postId}",
            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(300)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(300)
                )
            },
            arguments = listOf(
                navArgument("postId") { type = NavType.IntType }
            )
        ) { entry ->
            val postId = entry.arguments?.getInt("postId") ?: 0
            authViewModel.getPost(postId)
            authViewModel.getPostComments(postId)
            SelectedPostScreen(
                postCommentsUiState = authViewModel.postCommentsUiState,
                currentPostUiState = authViewModel.currentPostUiState,
                onBackButtonClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = NavigationDestination.FoundPerson.name,
            exitTransition = {
                fadeOut(animationSpec = tween(100))
            },
        ) {
            SearchPersonScreen(
                searchPersonsUiState = searchPersonsUiState,
                authUserId = (authViewModel.authUiState as AuthUiState.Success).user.id,
                onBackButtonClick = {
                    authViewModel.resetSearchTextAndSearchPersonsUiState()
                    navController.popBackStack()
                },
                updateSearchText = authViewModel::updateSearchText,
                onCurrentUserClick = { userId ->
                    navController.navigate(
                        NavigationDestination.UserDetails.name
                            + "/${userId}"
                            + "/${true}")
                }
            )
        }
    }
}