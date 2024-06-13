package br.com.nicole.smarteragenda.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.nicole.smarteragenda.ContactForm
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.ui.form.ContactFormScreen
import br.com.nicole.smarteragenda.ui.form.ContactFormViewModel
import br.com.nicole.smarteragenda.util.ID_CONTACT
import kotlinx.coroutines.launch

fun NavGraphBuilder.contactFormGraph(
    navController: NavHostController
) {
    composable(
        route = ContactForm.routeWithArguments,
        arguments = ContactForm.arguments
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(
            ID_CONTACT
        )?.let { idContact ->

            val viewModel = hiltViewModel<ContactFormViewModel>()
            val state by viewModel.uiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(state.birthday) {
                viewModel.defineTextoAniversario(
                    context.getString(R.string.aniversario)
                )
            }

            val coroutineScope = rememberCoroutineScope()
            ContactFormScreen(
                state = state,
                onClickSalvar = {
                    coroutineScope.launch {
                        viewModel.save()
                    }
                    navController.popBackStack()
                },
                onCarregarImagem = {
                    viewModel.carregaImagem(it)
                }
            )
        }
    }
}