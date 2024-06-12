package br.com.nicole.smarteragenda.SampleData

import br.com.nicole.smarteragenda.Model.Contact
import java.time.LocalDateTime


import java.util.Calendar

var contactsExample: List<Contact> = listOf(
    Contact(
        name = "Ana",
        phones = listOf("11999999999", "11888888888"),
        profilePic = "https://images.pexels.com/photos/3922294/pexels-photo-3922294.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        cpf = "123.456.789-00",
        uf = "SP",
        dataHoraCadastro = LocalDateTime.now(),
        birthday = Calendar.getInstance().time



        ),
    Contact(
        name = "Bill",
        phones = listOf("11999999999", "555555555"),
        profilePic = "https://images.pexels.com/photos/1415882/pexels-photo-1415882.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        birthday = Calendar.getInstance().time
    ),
    Contact(
        name = "Odes",
        phones = listOf("123456789", "555555555"),
        profilePic = "urlTesteParaDarErro",
        cpf = "987.654.321-00",
        uf = "MG",
        dataHoraCadastro = LocalDateTime.now(),
        birthday = Calendar.getInstance().time

    )

)