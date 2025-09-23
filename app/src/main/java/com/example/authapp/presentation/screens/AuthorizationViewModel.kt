package com.example.authapp.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AuthorizationUiState(
    val username: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isAuthError: Boolean? = null
)

class AuthorizationViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AuthorizationUiState())
    val uiState: StateFlow<AuthorizationUiState> = _uiState.asStateFlow()

    fun updateAuthError(flag: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isAuthError = flag
            )
        }
    }

    fun updateUsername(field: String) {
        _uiState.update { currentState ->
            currentState.copy(username = field)
        }
    }

    fun updatePassword(field: String) {
        _uiState.update { currentState ->
            currentState.copy(password = field)
        }
    }

    fun updatePasswordVisible() {
        _uiState.update { currentState ->
            currentState.copy(passwordVisible = !currentState.passwordVisible)
        }
        Log.e(TAG, "${uiState.value.passwordVisible}")
    }

    fun clearUiState() {
        _uiState.update { currentState ->
            currentState.copy(
                username = "",
                password = "",
                passwordVisible = false,
                isAuthError = null
            )
        }
    }
}