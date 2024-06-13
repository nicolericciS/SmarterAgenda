package br.com.nicole.smarteragenda.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicole.smarteragenda.database.ContactDao
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.util.ID_CONTACT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import javax.inject.Inject


@HiltViewModel

class ContactDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val contactDao: ContactDao
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
        CoroutineScope(Dispatchers.IO).launch {
            idContact?.let { id ->
                contactDao.searchForId(id)?.collect { contact ->
                    if (contact != null) {
                        _uiState.value = _uiState.value.copy(
                            id = contact.id,
                            name = contact.name,
                            phones = contact.phones,
                            profilePic = contact.profilePic,
                            cpf = contact.cpf,
                            uf = contact.uf,
                            dataHoraCadastro = LocalDateTime.now(),
                            birthday = contact.birthday,
                            appbarTitle = R.string.titulo_editar_contato
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            appbarTitle = R.string.adicionar_novo_contato
                        )
                    }
                }
            } ?: run {
                _uiState.value = _uiState.value.copy(
                    appbarTitle = R.string.adicionar_novo_contato
                )
            }
        }
    }

    suspend fun removeContact() {
        idContact?.let { contactDao.delete(it) }
    }
}

