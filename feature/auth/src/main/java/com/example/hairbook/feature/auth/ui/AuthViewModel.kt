package com.example.hairbook.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hairbook.feature.auth.domain.usecase.GetCurrentUserUseCase
import com.example.hairbook.feature.auth.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        getCurrentUserUseCase()
            .onEach { user ->
                _uiState.value = _uiState.value.copy(currentUserName = user?.displayName)
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            AuthUiEvent.SignInClicked -> viewModelScope.launch {
                signInUseCase("local-user", "HairBook User", null)
            }
        }
    }
}
