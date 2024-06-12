package br.com.nicole.smarteragenda.Ui.Details

import org.threeten.bp.LocalDateTime
import java.util.Date

data class ContactDetailsUiState(
    val id: Long = 0L,
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val profilePic: String = "",
    val cpf: String = "",
    val uf: String = "",
    val dataHoraCadastro: LocalDateTime? = null,
    val birthday: Date? = null,


    )
