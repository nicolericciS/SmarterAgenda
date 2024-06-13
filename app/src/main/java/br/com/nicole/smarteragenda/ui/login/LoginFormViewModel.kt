package br.com.nicole.smarteragenda.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import br.com.nicole.smarteragenda.preferences.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginFormViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
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

    suspend fun saveLogin() {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.USER] =
                _uiState.value.user
            preferences[PreferencesKey.PASSWORD] =
                _uiState.value.password
        }
    }
}
