package com.example.authapp.presentation.model

sealed class LoadingState {

    data object Loading : LoadingState()

    data object Success : LoadingState()

    data object Error : LoadingState()
}