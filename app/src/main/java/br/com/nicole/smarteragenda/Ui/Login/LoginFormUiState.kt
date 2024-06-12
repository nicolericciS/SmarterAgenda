package br.com.nicole.smarteragenda.Ui.Login

data class LoginFormUiState (
    val name: String = "",
    val user: String = "",
    val password: String = "",
    val onNameChanged: (String) -> Unit = {},
    val onUserChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {},
    val onClickSave: () -> Unit = {}
)