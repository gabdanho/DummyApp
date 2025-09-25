package com.example.authapp.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.domain.interfaces.repository.UserRepository
import com.example.authapp.domain.model.ApiResult
import com.example.authapp.presentation.mapper.toDomainLayer
import com.example.authapp.presentation.mapper.toPresentationLayer
import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.StringResNamePresentation
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.AuthRequest
import com.example.authapp.presentation.navigation.Navigator
import com.example.authapp.presentation.navigation.model.AppGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для [AuthScreen].
 *
 * @param navigator навигация между экранами
 * @param userRepository репозиторий пользователей
 */
@HiltViewModel
class AuthScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthScreenUiState())
    val uiState: StateFlow<AuthScreenUiState> = _uiState.asStateFlow()

    fun usernameValueUpdate(value: String) {
        _uiState.update { state -> state.copy(usernameValue = value) }
    }

    fun passwordValueUpdate(value: String) {
        _uiState.update { state -> state.copy(passwordValue = value) }
    }

    fun isPasswordHiddenUpdate() {
        _uiState.update { state -> state.copy(isPasswordHidden = !state.isPasswordHidden) }
    }

    fun login() {
        if (!isAllFieldFilled()) {
            _uiState.update { state -> state.copy(uiMessage = UiMessage(textResName = StringResNamePresentation.ERROR_ALL_FIELDS_NOT_FILLED)) }
            return
        }

        viewModelScope.launch {
            _uiState.update { state -> state.copy(loadingState = LoadingState.Loading) }
            val state = _uiState.value

            val authRequestDomain = AuthRequest(
                username = state.usernameValue.trim(),
                password = state.passwordValue
            ).toDomainLayer()

            when (val result = userRepository.authUser(authRequest = authRequestDomain)) {
                is ApiResult.Success -> {
                    navigator.navigate(
                        destination = AppGraph.MainScreen(userLogin = result.data.toPresentationLayer()),
                        navOptions = { popUpTo(0) { inclusive } }
                    )
                }

                is ApiResult.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            uiMessage = UiMessage(
                                textResName = StringResNamePresentation.ERROR_AUTH,
                                details = result.message
                            ),
                            loadingState = LoadingState.Error
                        )
                    }
                }
            }
        }
    }

    fun clearMessage() {
        _uiState.update { state -> state.copy(uiMessage = null) }
    }

    private fun isAllFieldFilled(): Boolean {
        val state = _uiState.value

        return state.passwordValue.isNotBlank() && state.usernameValue.isNotBlank()
    }
}