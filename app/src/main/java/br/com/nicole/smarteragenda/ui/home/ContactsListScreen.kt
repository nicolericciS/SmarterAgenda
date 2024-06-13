package br.com.nicole.smarteragenda.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nicole.smarteragenda.model.Contact
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.sampleData.contactsExample
import br.com.nicole.smarteragenda.Theme.SmarterAgendaTheme
import br.com.nicole.smarteragenda.ui.components.AsyncProfilePic


@Composable
fun ContactsListScreen(
    state: ContactsListUiState,
    modifier: Modifier = Modifier,
    onClickDesloga: () -> Unit = {},
    onClickAbreDetalhes: (Long) -> Unit = {},
    onClickAbreCadastro: () -> Unit = {},
    onClickPesquisa: (String) -> Unit = {},
    onClickShowPesquisa: () -> Unit = {}
) {

    Scaffold(
        topBar = {
            if (state.isShowPesquisa) {
                PesquisaDadosTopBar(
                    textoDePesquisa = state.textoPesquisa,
                    onClickPesquisa = onClickPesquisa,
                    onClickShowPesquisa = onClickShowPesquisa
                )
            } else {
                AppBarContactsList(onClickDesloga = onClickDesloga, onClickShowPesquisa)
            }},
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickAbreCadastro() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.tertiary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.adicionar_novo_contato)
                )
            }
        }) { paddingValues ->

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(paddingValues)) {
            items(state.contacts) { contact ->
                ContactItem(contact) { idContact ->
                    onClickAbreDetalhes(idContact)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarContactsList(onClickDesloga: () -> Unit,
                       onClickShowPesquisa: () -> Unit = {}) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name), color = Color.White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            IconButton(
                onClick = onClickDesloga
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.deslogar)
                )
            }
            IconButton(
                onClick = onClickShowPesquisa
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.pesquisar)
                )
            }
        }
    )
}

@Composable
fun ContactItem(
    contact: Contact,
    onClick: (Long) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier.clickable { onClick(contact.id) }
    ) {
        Row(Modifier.padding(16.dp)) {
            AsyncProfilePic(
                urlImagem = contact.profilePic,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column(
                Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = contact.name,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = contact.cpf,
                    fontWeight = FontWeight.Normal,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = contact.uf,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Preview
@Composable
fun ListaContatosPreview() {
    SmarterAgendaTheme {
        ContactsListScreen(
            state = ContactsListUiState(contactsExample)
        )
    }
}

@Preview
@Composable
fun ContatoItemPreview() {
    SmarterAgendaTheme {
        ContactItem(contactsExample.first()) {}
    }
}


@Composable
fun PesquisaDadosTopBar(
    textoDePesquisa: String,
    onClickPesquisa: (String) -> Unit,
    onClickShowPesquisa: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = textoDePesquisa,
            onValueChange = { newValue ->
                onClickPesquisa(newValue)
            },
            modifier = modifier
                .padding(10.dp),
            shape = RoundedCornerShape(100),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Icone de Lupa")
            },
            label = { Text("Pesquisar") },
            placeholder = {
                Text("Digite o nome do contato ...")
            },
        )
        IconButton(
            onClick = onClickShowPesquisa
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                tint = Color.Black,
                contentDescription = "Sair do modo de pesquisa"
            )
        }

    }
}

@Preview
@Composable
fun PesquisaDadosTopBarPreview() {
    SmarterAgendaTheme {
        PesquisaDadosTopBar(
            textoDePesquisa = "Clau",
            onClickPesquisa = {},
        )
    }
}
