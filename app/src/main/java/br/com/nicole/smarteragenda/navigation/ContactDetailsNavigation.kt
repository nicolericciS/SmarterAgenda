package br.com.nicole.smarteragenda.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.nicole.smarteragenda.ContactDetails
import br.com.nicole.smarteragenda.util.extensions.showMessage
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.ui.details.ContactDetailsScreen
import br.com.nicole.smarteragenda.ui.details.ContactDetailsViewModel
import br.com.nicole.smarteragenda.ui.navigateToContactForm

import br.com.nicole.smarteragenda.util.ID_CONTACT
import kotlinx.coroutines.launch

fun NavGraphBuilder.contactDetailsGraph(
    navController: NavHostController
) {
    composable(
        route = ContactDetails.routeWithArguments,
        arguments = ContactDetails.arguments
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(
            ID_CONTACT
        )?.let { idContact->

            val viewModel = hiltViewModel<ContactDetailsViewModel>()
            val state by viewModel.uiState.collectAsState()

            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            ContactDetailsScreen(
                state = state,
                onClickBack = { navController.popBackStack() },
                onRemoveContact = {
                    scope.launch {
                        viewModel.removeContact()
                        context.showMessage(context.getString(R.string.contato_apagado))
                    }
                    navController.popBackStack()
                },
                onClickEdit = { navController.navigateToContactForm(idContact) })
        }
    }
}