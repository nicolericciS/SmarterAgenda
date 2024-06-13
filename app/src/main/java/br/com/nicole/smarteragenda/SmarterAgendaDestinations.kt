package br.com.nicole.smarteragenda

import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.nicole.smarteragenda.util.ID_CONTACT

sealed class SmarterAgendaDestinations(val route: String) {
    object LoginGraph : SmarterAgendaDestinations("graph_login")
    object HomeGraph : SmarterAgendaDestinations("graph_home")
    object SplashScreen : SmarterAgendaDestinations("splashScreen")
    object ContactsList : SmarterAgendaDestinations("contacts_list")
    object LoginForm : SmarterAgendaDestinations("form_login")
    object Login : SmarterAgendaDestinations("login")
}

object ContactForm {
    const val route = "contact_form"
    const val routeWithArguments = "$route/{$ID_CONTACT}"
    val arguments = listOf(
        navArgument(ID_CONTACT) {
            defaultValue = 0L
            type = NavType.LongType
        }
    )
}

object ContactDetails {
    const val route = "contact_details"
    const val routeWithArguments = "$route/{$ID_CONTACT}"
    val arguments = listOf(
        navArgument(ID_CONTACT) {
            defaultValue = 0L
            type = NavType.LongType
        }
    )
}