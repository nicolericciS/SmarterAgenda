package br.com.nicole.smarteragenda.Ui.Details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicole.smarteragenda.Util.ID_CONTACT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactDetailsViewModel(
savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val idContact = savedStateHandle.get<Long>(ID_CONTACT)

    private val _uiState = MutableStateFlow(ContactDetailsUiState())
    val uiState: StateFlow<ContactDetailsUiState>
    get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadContact()
        }
    }

    private suspend fun loadContact() {
    }

    suspend fun removeContact() {
    }
}
