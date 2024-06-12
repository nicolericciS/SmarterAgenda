package br.com.nicole.smarteragenda.Ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicole.smarteragenda.Database.ContactDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsListViewModel @Inject constructor(
    private val contactDao: ContactDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactsListUiState())
    val uiState: StateFlow<ContactsListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val contacts = contactDao.searchAll()
            _uiState.value = _uiState.value.copy(
                contacts = contacts
            )
        }
    }

}