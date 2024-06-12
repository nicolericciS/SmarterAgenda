package br.com.nicole.smarteragenda.Navigation

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
import br.com.nicole.smarteragenda.Database.SmarterAgendaDatabase
import br.com.nicole.smarteragenda.Model.Contact
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.Ui.Form.ContactFormScreen
import br.com.nicole.smarteragenda.Ui.Form.ContactFormViewModel
import br.com.nicole.smarteragenda.Util.ID_CONTACT
import kotlinx.coroutines.launch
import java.time.LocalDateTime

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

            val contactDao = SmarterAgendaDatabase.getDatabase(context).contactDao()

            val coroutineScope = rememberCoroutineScope()
            ContactFormScreen(
                state = state,
                onClickSalvar = {
                    with(state) {
                        coroutineScope.launch {
                            contactDao.insert(
                                Contact(
                                    name = name,
                                    phones = phones,
                                    profilePic = profilePic,
                                    cpf = cpf,
                                    uf = uf,
                                    dataHoraCadastro = LocalDateTime.now(),
                                    birthday = birthday
                                )
                            )
                        }
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