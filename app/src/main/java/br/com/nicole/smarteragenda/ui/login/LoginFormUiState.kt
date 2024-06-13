package br.com.nicole.smarteragenda.ui.login

data class LoginFormUiState (
    val name: String = "",
    val user: String = "",
    val password: String = "",
    val onNameChanged: (String) -> Unit = {},
    val onUserChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {},
    val onClickSave: () -> Unit = {}
)