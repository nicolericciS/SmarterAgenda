package br.com.nicole.smarteragenda.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

import java.util.Date
@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String = "",
    val phones: List<String>,
    val profilePic: String = "",
    val cpf: String = "",
    val uf: String = "",
    val dataHoraCadastro: LocalDateTime? = null,
    val birthday: Date? = null,
)


