package br.com.nicole.smarteragenda.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.nicole.smarteragenda.SmarterAgendaDestinations
import br.com.nicole.smarteragenda.ui.login.LoginFormScreen
import br.com.nicole.smarteragenda.ui.login.LoginFormViewModel
import br.com.nicole.smarteragenda.ui.login.LoginScreen
import br.com.nicole.smarteragenda.ui.login.LoginViewModel
import br.com.nicole.smarteragenda.ui.navegaLimpo
import kotlinx.coroutines.launch

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
            val coroutineScope = rememberCoroutineScope()

            if (state.loggedIn) {
                LaunchedEffect(Unit) {
                    navController.navegaLimpo(SmarterAgendaDestinations.HomeGraph.route)
                }
            }

            LoginScreen(
                state = state,
                onClickLogar = {
                    coroutineScope.launch {
                        viewModel.tryLogin()
                    }
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
            val coroutineScope = rememberCoroutineScope()

            LoginFormScreen(
                state = state,
                onSalvar = {
                    coroutineScope.launch {
                        viewModel.saveLogin()
                    }
                    navController.navegaLimpo(SmarterAgendaDestinations.Login.route)
                }
            )
        }
    }
}