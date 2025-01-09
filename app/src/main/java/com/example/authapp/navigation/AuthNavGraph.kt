package com.example.authapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.authapp.model.AuthUiState
import com.example.authapp.ui.theme.screens.AuthScreen
import com.example.authapp.ui.theme.screens.AuthViewModel
import com.example.authapp.ui.theme.screens.AuthorizationViewModel
import com.example.authapp.ui.theme.screens.MainScreen
import com.example.authapp.ui.theme.screens.SearchPersonScreen
import com.example.authapp.ui.theme.screens.SelectedPostScreen
import com.example.authapp.ui.theme.screens.UserDetailsScreen
import com.example.authapp.ui.theme.screens.UserPostsScreen

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val authViewModel = hiltViewModel<AuthViewModel>()
    val authorizationViewModel = AuthorizationViewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Start.name,
        modifier = modifier
    ) {
        composable(route = NavigationDestination.Start.name) {
            AuthScreen(
                authUser = authViewModel::authUser,
                authorizationViewModel = authorizationViewModel,
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
                    navController.navigate(NavigationDestination.UserDetails.name
                            + "/${(authViewModel.authUiState as AuthUiState.Success).user.id}"
                            + "/${false}"
                    )
                },
                onMyPostsClick = {
                    navController.navigate(NavigationDestination.Posts.name
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
                authUserId = (authViewModel.authUiState as AuthUiState.Success).user.id,
                authViewModel = authViewModel,
                onBackButtonClick = {
                    authViewModel.resetSearchTextAndSearchPersonsUiState()
                    navController.popBackStack()
                },
                onCurrentUserClick = { userId ->
                    navController.navigate(NavigationDestination.UserDetails.name
                            + "/${userId}"
                            + "/${true}")
                }
            )
        }
    }
}