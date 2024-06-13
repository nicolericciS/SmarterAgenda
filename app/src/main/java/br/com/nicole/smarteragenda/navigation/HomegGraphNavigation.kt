package br.com.nicole.smarteragenda.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.nicole.smarteragenda.preferences.dataStore
import br.com.nicole.smarteragenda.SmarterAgendaDestinations
import br.com.nicole.smarteragenda.ui.home.ContactsListScreen
import br.com.nicole.smarteragenda.ui.home.ContactsListViewModel
import br.com.nicole.smarteragenda.ui.navegaParaLoginDeslogado
import br.com.nicole.smarteragenda.ui.navigateToContactForm
import br.com.nicole.smarteragenda.ui.navigateToDetails
import kotlinx.coroutines.launch

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
            val coroutineScope = rememberCoroutineScope()
            val dataStore = LocalContext.current.dataStore

            ContactsListScreen(
                state = state,
                onClickAbreDetalhes = { idContact ->
                    navController.navigateToDetails(idContact)
                },
                onClickAbreCadastro = {
                    navController.navigateToContactForm()
                },
                onClickDesloga = {
                    coroutineScope.launch {
                        viewModel.logOff()
                        navController.navegaParaLoginDeslogado()
                    }
                },
                onClickShowPesquisa = {
                    viewModel.showSearch()
                },
                onClickPesquisa = {
                    viewModel.searchContact(it)
                }
            )
        }
    }
}