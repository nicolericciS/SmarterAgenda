package br.com.nicole.smarteragenda.Ui.SplashScreen

data class SplashScreenUiState(
    val appState: AppState = AppState.Loading
)

sealed class AppState {
    object Loading : AppState()
    object LoggedIn : AppState()
    object LoggedOut : AppState()
}