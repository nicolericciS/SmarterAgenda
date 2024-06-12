package br.com.nicole.smarteragenda.Ui.Login


data class LoginUiState(
    val user: String = "",
    val password: String = "",
    val showError: Boolean = false,
    val onError: (Boolean) -> Unit = {},
    val onUserChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {},
    val onClickLogin: () -> Unit = {},
    val loggedIn: Boolean = false
)

