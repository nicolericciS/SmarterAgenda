package br.com.nicole.smarteragenda.Navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.nicole.smarteragenda.SmarterAgendaDestinations
import br.com.nicole.smarteragenda.Ui.SplashScreen.AppState
import br.com.nicole.smarteragenda.Ui.SplashScreen.SplashScreenViewModel
import br.com.nicole.smarteragenda.Ui.navegaLimpo

fun NavGraphBuilder.splashGraph(
    navController: NavHostController
) {
    composable(
        route = SmarterAgendaDestinations.SplashScreen.route
    ) {
        val viewModel = hiltViewModel<SplashScreenViewModel>()
        val state by viewModel.uiState.collectAsState()

        when (state.appState) {
            AppState.Loading -> Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            AppState.LoggedOut -> {
                LaunchedEffect(Unit) {
                    navController.navegaLimpo(SmarterAgendaDestinations.Login.route)
                }
            }
            AppState.LoggedIn -> {
                LaunchedEffect(Unit) {
                    navController.navegaLimpo(SmarterAgendaDestinations.HomeGraph.route)
                }
            }
        }
    }
}