package br.com.nicole.smarteragenda.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
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
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
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

    suspend fun tryLogin() {

        dataStore.data.collect { preferences ->
            val password = preferences[PreferencesKey.PASSWORD]
            val user = preferences[PreferencesKey.USER]
            if (user == _uiState.value.user && password == _uiState.value.password) {
                dataStore.edit {
                    it[booleanPreferencesKey("logado")] = true
                }
                loginUser()
            } else {
                _uiState.value.onError(true)
            }
        }
    }

    private fun loginUser() {
        _uiState.value = _uiState.value.copy(
            loggedIn = true
        )
    }
}
