package br.com.nicole.smarteragenda.Extensions

import android.content.Context
import android.widget.Toast

fun Context.showMessage(
    texto: String,
    duracao: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(
        this,
        texto,
        duracao
    ).show()
}