package br.com.nicole.smarteragenda.Ui.Home

import br.com.nicole.smarteragenda.Model.Contact

data class ContactsListUiState(
    val contacts: List<Contact> = emptyList(),
    val logado: Boolean = true

)
