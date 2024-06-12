package br.com.nicole.smarteragenda.Ui.Form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.nicole.smarteragenda.Extensions.convertToDate
import br.com.nicole.smarteragenda.Extensions.convertToString
import br.com.nicole.smarteragenda.Util.ID_CONTACT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime


class ContactFormViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val idContato = savedStateHandle.get<Long>(ID_CONTACT)

    private val _uiState = MutableStateFlow(ContactFormUiState())
    val uiState: StateFlow<ContactFormUiState>
        get() = _uiState.asStateFlow()


    init {

        _uiState.update { state ->
            state.copy(onNameChanged = {
                _uiState.value = _uiState.value.copy(
                    name = it
                )
                },
                onPhoneChanged = { index, phone ->
                    val updatedPhones = _uiState.value.phones.toMutableList().apply {
                        this[index] = phone
                    }
                    _uiState.value = _uiState.value.copy(
                        phones = updatedPhones
                    )
                },
                onAddPhone = {
                    val updatedPhones = _uiState.value.phones.toMutableList().apply {
                        add("")
                    }
                    _uiState.value = _uiState.value.copy(
                        phones = updatedPhones
                    )
                },
                onRemovePhone = { index ->
                    val updatedPhones = _uiState.value.phones.toMutableList().apply {
                        removeAt(index)
                    }
                    _uiState.value = _uiState.value.copy(
                        phones = updatedPhones
                    )
                }, onProfilePicChanged = {
                    _uiState.value = _uiState.value.copy(
                        profilePic = it
                    )
                }, onCpfChanged = {
                    _uiState.value = _uiState.value.copy(
                        cpf = it
                    )
                },
                onUfChanged = {
                    _uiState.value = _uiState.value.copy(
                        uf = it
                    )
                },
                ondataHoraCadastroChanged = {
                    _uiState.value = _uiState.value.copy(
                        dataHoraCadastro = LocalDateTime.now()
                    )
                },

                onBirthdayChanged = {
                    _uiState.value = _uiState.value.copy(
                        birthday = it.convertToDate(), mostrarCaixaDialogoData = false
                    )
                }, onMostrarCaixaDialogoImagem = {
                    _uiState.value = _uiState.value.copy(
                        mostrarCaixaDialogoImagem = it
                    )
                }, onMostrarCaixaDialogoData = {
                    _uiState.value = _uiState.value.copy(
                        mostrarCaixaDialogoData = it
                    )
                }
            )
        }
    }

    fun defineTextoAniversario(textoAniversario: String) {
        val textoAniversairo = _uiState.value.birthday?.convertToString() ?: textoAniversario

        _uiState.update {
            it.copy(birthdayText = textoAniversairo)
        }
    }

    fun carregaImagem(url: String) {
        _uiState.value = _uiState.value.copy(
            profilePic = url, mostrarCaixaDialogoImagem = false
        )
    }
}