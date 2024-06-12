package br.com.nicole.smarteragenda.Navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.nicole.smarteragenda.ContactDetails
import br.com.nicole.smarteragenda.Extensions.showMessage
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.Ui.Details.ContactDetailsScreen
import br.com.nicole.smarteragenda.Ui.Details.ContactDetailsViewModel
import br.com.nicole.smarteragenda.Ui.navigateToContactForm

import br.com.nicole.smarteragenda.Util.ID_CONTACT
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
                        context.showMessage(context.getString(R.string.contato_apagado))
                    }
                    navController.popBackStack()
                },
                onClickEdit = { navController.navigateToContactForm(idContact) })
        }
    }
}