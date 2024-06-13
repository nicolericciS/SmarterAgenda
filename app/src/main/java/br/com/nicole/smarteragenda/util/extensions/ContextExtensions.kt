package br.com.nicole.smarteragenda.util.extensions

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