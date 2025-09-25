package com.example.authapp.presentation.screens.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.authapp.presentation.navigation.NavigationAction
import com.example.authapp.presentation.navigation.ObserveAsEvents
import com.example.authapp.presentation.navigation.appGraph

/**
 * Корневой экран навигации приложения.
 *
 * @param viewModel [NavigationScreenViewModel].
 */
@Composable
fun NavigationScreen(
    viewModel: NavigationScreenViewModel = hiltViewModel<NavigationScreenViewModel>(),
) {
    val navigator = viewModel.navigator
    val navController = rememberNavController()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
            is NavigationAction.Navigate -> navController.navigate(
                action.navigationDestination
            ) {
                action.navOptions(this)
            }

            NavigationAction.NavigateToPopBackStack -> navController.popBackStack()
        }
    }

    NavHost(
        navController = navController,
        startDestination = navigator.startDestination,
        contentAlignment = Alignment.TopCenter
    ) {
        appGraph()
    }
}