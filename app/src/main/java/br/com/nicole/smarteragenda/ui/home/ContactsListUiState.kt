package br.com.nicole.smarteragenda.ui.home

import br.com.nicole.smarteragenda.model.Contact

data class ContactsListUiState(
    val contacts: List<Contact> = emptyList(),
    val logado: Boolean = true,
    val isShowPesquisa: Boolean = false,
    val textoPesquisa: String = ""

)
