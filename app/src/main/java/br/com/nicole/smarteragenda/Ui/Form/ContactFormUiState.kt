package br.com.nicole.smarteragenda.Ui.Form

import androidx.annotation.StringRes
import br.com.nicole.smarteragenda.R
import org.threeten.bp.LocalDate


import org.threeten.bp.Period
import java.time.LocalDateTime

import java.util.Date

data class ContactFormUiState(
    val id: Long = 0L,
    val name: String = "",
    //val surname: String = "",
    val phones: List<String> = listOf(""),
    val profilePic: String = "",
    val cpf: String = "",
    val uf: String = "",
    val dataHoraCadastro: LocalDateTime? = null,
    val birthday: Date? = null,
    val mostrarCaixaDialogoImagem: Boolean = false,
    val mostrarCaixaDialogoData: Boolean = false,
    val onNameChanged: (String) -> Unit = {},
    //val onSurnameChanged: (String) -> Unit = {},
    val onPhoneChanged: (Int, String) -> Unit = { _, _ -> },
    val onAddPhone: () -> Unit = {},
    val onRemovePhone: (Int) -> Unit = {},
    val onProfilePicChanged: (String) -> Unit = {},
    val onCpfChanged: (String) -> Unit = {},
    val onUfChanged: (String) -> Unit = {},
    val ondataHoraCadastroChanged: (String) -> Unit = {},
    val onBirthdayChanged: (String) -> Unit = {},
    val onMostrarCaixaDialogoImagem: (mostrar: Boolean) -> Unit = {},
    val onMostrarCaixaDialogoData: (mostrar: Boolean) -> Unit = {},
    val birthdayText: String = "",
    @StringRes val appbarTitle: Int? = R.string.titulo_cadastro_contato,
) {
    fun isCpfRequired(): Boolean = uf.uppercase() == "SP"
    fun isUnderage(): Boolean {
        if (birthday == null) return false
        return try {
            val birthDate = org.threeten.bp.Instant.ofEpochMilli(birthday.time)
                .atZone(org.threeten.bp.ZoneId.systemDefault())
                .toLocalDate()
            val today = LocalDate.now()
            Period.between(birthDate, today).years < 18
        } catch (e: Exception) {
            false
        }
    }

    fun isValidCpf(cpf: String): Boolean {
        if (cpf.length != 11 || cpf.all { it == cpf[0] }) return false

        val numbers = cpf.map { it.toString().toInt() }
        val dv1 = calculateCpfDigit(numbers.subList(0, 9))
        val dv2 = calculateCpfDigit(numbers.subList(0, 9) + dv1)

        return numbers[9] == dv1 && numbers[10] == dv2
    }

    private fun calculateCpfDigit(numbers: List<Int>): Int {
        val weights = (numbers.size + 1 downTo 2).toList()
        val sum = numbers.zip(weights) { n, w -> n * w }.sum()
        val remainder = sum % 11
        return if (remainder < 2) 0 else 11 - remainder
    }
}
