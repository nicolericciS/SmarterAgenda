package br.com.nicole.smarteragenda.ui.splashScreen

data class SplashScreenUiState(
    val appState: AppState = AppState.Loading
)

sealed class AppState {
    object Loading : AppState()
    object LoggedIn : AppState()
    object LoggedOut : AppState()
}