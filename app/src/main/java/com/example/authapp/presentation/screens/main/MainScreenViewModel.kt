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

    fun onMyDetailsClick() {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.UserDetailsScreen
            )
        }
    }

    fun onMyPostsClick() {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.UserPostsScreen
            )
        }
    }

    fun onSearchPersonClick() {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.SearchPerson
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