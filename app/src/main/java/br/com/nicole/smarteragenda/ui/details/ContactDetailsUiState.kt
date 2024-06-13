package br.com.nicole.smarteragenda.ui.details

import androidx.annotation.StringRes
import br.com.nicole.smarteragenda.R
import org.threeten.bp.LocalDateTime
import java.util.Date

data class ContactDetailsUiState(
    val id: Long = 0L,
    val name: String = "",
    val phones: List<String> = emptyList(),
    val profilePic: String = "",
    val cpf: String = "",
    val uf: String = "",
    val dataHoraCadastro: LocalDateTime? = null,
    val birthday: Date? = null,
    @StringRes val appbarTitle: Int? = R.string.titulo_cadastro_contato,


    )
