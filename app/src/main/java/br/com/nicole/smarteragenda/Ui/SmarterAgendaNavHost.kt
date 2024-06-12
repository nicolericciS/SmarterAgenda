package br.com.nicole.smarteragenda.Ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.nicole.smarteragenda.ContactDetails
import br.com.nicole.smarteragenda.ContactForm
import br.com.nicole.smarteragenda.Navigation.contactDetailsGraph
import br.com.nicole.smarteragenda.Navigation.contactFormGraph
import br.com.nicole.smarteragenda.Navigation.homeGraph
import br.com.nicole.smarteragenda.Navigation.loginGraph
import br.com.nicole.smarteragenda.SmarterAgendaDestinations

@Composable
fun SmarterAgendaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SmarterAgendaDestinations.LoginGraph.route,
        modifier = modifier
    ) {
        loginGraph(navController)
        homeGraph(navController)
        contactFormGraph(navController)
        contactDetailsGraph(navController)
    }
}

fun NavHostController.navegaDireto(route: String) = this.navigate(route) {
    popUpTo(this@navegaDireto.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navegaLimpo(route: String) = this.navigate(route) {
    popUpTo(0)
}

fun NavHostController.navigateToDetails(idContact: Long) {
    navegaDireto("${ContactDetails.route}/$idContact")
}

fun NavHostController.navigateToContactForm(idContact: Long = 0L) {
    navigate("${ContactForm.route}/$idContact")
}

fun NavHostController.navegaParaLoginDeslogado() {
    popBackStack(SmarterAgendaDestinations.ContactsList.route, true)
    navegaDireto(SmarterAgendaDestinations.LoginGraph.route)
}


