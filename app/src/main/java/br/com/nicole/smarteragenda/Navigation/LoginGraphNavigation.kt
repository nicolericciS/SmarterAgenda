package br.com.nicole.smarteragenda.Navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.nicole.smarteragenda.SmarterAgendaDestinations
import br.com.nicole.smarteragenda.Ui.Login.LoginFormScreen
import br.com.nicole.smarteragenda.Ui.Login.LoginFormViewModel
import br.com.nicole.smarteragenda.Ui.Login.LoginScreen
import br.com.nicole.smarteragenda.Ui.Login.LoginViewModel
import br.com.nicole.smarteragenda.Ui.navegaLimpo

fun NavGraphBuilder.loginGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = SmarterAgendaDestinations.Login.route,
        route = SmarterAgendaDestinations.LoginGraph.route
    ) {
        composable(
            route = SmarterAgendaDestinations.Login.route,
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.uiState.collectAsState()

            if (state.loggedIn) {
                LaunchedEffect(Unit) {
                    navController.navegaLimpo(SmarterAgendaDestinations.HomeGraph.route)
                }
            }

            LoginScreen(
                state = state,
                onClickLogar = {
                    viewModel.tryLogin()
                },
                onClickCriarLogin = {
                    navController.navigate(SmarterAgendaDestinations.LoginForm.route)
                }
            )
        }

        composable(
            route = SmarterAgendaDestinations.LoginForm.route,
        ) {
            val viewModel = hiltViewModel<LoginFormViewModel>()
            val state by viewModel.uiState.collectAsState()

            LoginFormScreen(
                state = state,
                onSalvar = {
                    navController.navegaLimpo(SmarterAgendaDestinations.Login.route)
                }
            )
        }
    }
}