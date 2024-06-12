package br.com.nicole.smarteragenda.Ui.Login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onUserChanged = {
                    _uiState.value = _uiState.value.copy(
                        user = it
                    )
                },
                onPasswordChanged = {
                    _uiState.value = _uiState.value.copy(
                        password = it
                    )
                },
                onError = {
                    _uiState.value = _uiState.value.copy(
                        showError = it
                    )
                },
            )
        }
    }

    fun tryLogin() {
        loginUser()
    }

    private fun loginUser() {
        _uiState.value = _uiState.value.copy(
            loggedIn = true
        )
    }
}
