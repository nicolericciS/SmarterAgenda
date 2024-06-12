package br.com.nicole.smarteragenda.Ui.Home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import br.com.nicole.smarteragenda.Database.SmarterAgendaDatabase
import br.com.nicole.smarteragenda.Model.Contact
import br.com.nicole.smarteragenda.SampleData.contactsExample
import br.com.nicole.smarteragenda.Theme.SmarterAgendaTheme
import br.com.nicole.smarteragenda.Ui.SmarterAgendaNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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