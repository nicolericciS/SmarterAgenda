package br.com.nicole.smarteragenda.Navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.nicole.smarteragenda.SmarterAgendaDestinations
import br.com.nicole.smarteragenda.Ui.Home.ContactsListScreen
import br.com.nicole.smarteragenda.Ui.Home.ContactsListViewModel
import br.com.nicole.smarteragenda.Ui.navigateToContactForm
import br.com.nicole.smarteragenda.Ui.navigateToDetails

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = SmarterAgendaDestinations.ContactsList.route,
        route = SmarterAgendaDestinations.HomeGraph.route,
    ) {
        composable(route = SmarterAgendaDestinations.ContactsList.route) {
            val viewModel = hiltViewModel<ContactsListViewModel>()
            val state by viewModel.uiState.collectAsState()

            ContactsListScreen(
                state = state,
                onClickAbreDetalhes = { idContact ->
                    navController.navigateToDetails(idContact)
                },
                onClickAbreCadastro = {
                    navController.navigateToContactForm()
                },
                onClickDesloga = {


                })
        }
    }
}