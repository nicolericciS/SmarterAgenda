package br.com.nicole.smarteragenda.Ui.Login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginFormViewModel (
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginFormUiState())
    val uiState: StateFlow<LoginFormUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onNameChanged = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
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
            )
        }
    }
}
