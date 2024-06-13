package br.com.nicole.smarteragenda.ui.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicole.smarteragenda.database.ContactDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsListViewModel @Inject constructor(
    private val contactDao: ContactDao,
    private val dataStore:DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactsListUiState())
    val uiState: StateFlow<ContactsListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val contacts = contactDao.searchAll()
            contacts.collect(){searchedContacts ->
                _uiState.value = _uiState.value.copy(
                    contacts = searchedContacts
                )
            }

        }

        searchAllContacts()
    }


    private fun searchAllContacts(){
        viewModelScope.launch {
            val contacts = contactDao.searchAll()

            contacts.collect() { contatosBuscados ->
                _uiState.value = _uiState.value.copy(
                    contacts = contatosBuscados
                )
            }
        }
    }

    fun showSearch(){
        searchAllContacts()
        _uiState.value = _uiState.value.copy(
            isShowPesquisa = !_uiState.value.isShowPesquisa,
            textoPesquisa = ""
        )
    }

    fun searchContact(entrada: String) {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                textoPesquisa = entrada
            )

            contactDao.searchContact(entrada).collect{
                _uiState.value = _uiState.value.copy(
                    contacts = it,
                )
            }
        }
    }

    suspend fun logOff() {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey("logado")] = false
        }
    }

}