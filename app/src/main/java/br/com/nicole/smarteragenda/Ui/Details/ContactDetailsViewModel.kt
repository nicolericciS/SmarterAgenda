package br.com.nicole.smarteragenda.Ui.Details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicole.smarteragenda.Database.ContactDao
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.Util.ID_CONTACT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
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
            idContact?.let {
                val contact = contactDao.searchForId(idContact)
                contact?.collect {
                    with(it) {
                        _uiState.value = _uiState.value.copy(
                            id = id,
                            name = name,
                            phones = phones,
                            profilePic = profilePic,
                            cpf = cpf,
                            uf = uf,
                            dataHoraCadastro = LocalDateTime.now(),
                            birthday = birthday,
                            appbarTitle = R.string.titulo_editar_contato
                        )
                    }
                }
            }
        }
    }

    suspend fun removeContact() {
        idContact?.let { contactDao.delete(it) }
    }


}
