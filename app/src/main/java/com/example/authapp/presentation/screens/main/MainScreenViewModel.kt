package com.example.authapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.presentation.navigation.Navigator
import com.example.authapp.presentation.navigation.model.AppGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {

    fun onMyDetailsClick(userId: Int) {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.UserDetailsScreen(id = userId)
            )
        }
    }

    fun onMyPostsClick(userId: Int) {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.UserPostsScreen(id = userId)
            )
        }
    }

    fun onFoundPersonClick(authUserId: Int) {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.SearchPerson(authUserId = authUserId)
            )
        }
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.AuthScreen,
                navOptions = { popUpTo(0) { inclusive } }
            )
        }
    }
}