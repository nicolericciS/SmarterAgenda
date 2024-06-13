package br.com.nicole.smarteragenda.ui.splashScreen

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicole.smarteragenda.preferences.PreferencesKey.LOGGEDIN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashScreenUiState())
    val uiState: StateFlow<SplashScreenUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            definesInitialDestination()
        }
    }

    private suspend fun definesInitialDestination() {
        delay(1000)
        dataStore.data.collect{
            val appState = if (it[LOGGEDIN] == true) {
                AppState.LoggedIn
            } else {
                AppState.LoggedOut
            }
            _uiState.value = _uiState.value.copy(
                appState = appState
            )
        }
    }
}