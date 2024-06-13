package br.com.nicole.smarteragenda.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name ="login")

object PreferencesKey{
    val PASSWORD = stringPreferencesKey("password")
    val USER = stringPreferencesKey("user")
    val LOGGEDIN = booleanPreferencesKey("logado")
}