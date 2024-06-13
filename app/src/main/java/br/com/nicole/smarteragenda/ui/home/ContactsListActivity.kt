package br.com.nicole.smarteragenda.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import br.com.nicole.smarteragenda.Theme.SmarterAgendaTheme
import br.com.nicole.smarteragenda.ui.SmarterAgendaNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmarterAgendaTheme {
                val navController = rememberNavController()
                SmarterAgendaNavHost(
                    navController = navController,
                )
            }
        }
    }
}