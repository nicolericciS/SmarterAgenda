@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.nicole.smarteragenda.Ui.Details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nicole.smarteragenda.Extensions.convertToString
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.Theme.SmarterAgendaTheme
import br.com.nicole.smarteragenda.Ui.Components.AsyncProfilePic

@Composable
fun ContactDetailsScreen(
    state: ContactDetailsUiState,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {},
    onClickEdit: () -> Unit = {},
    onRemoveContact: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            ContactDetailsAppBar(
                onClickBack = onClickBack,
                onClickRemove = onRemoveContact,
                onClickEdit = onClickEdit
            )
        },
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncProfilePic(
                urlImagem = state.profilePic,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = state.name,
                style = MaterialTheme.typography.headlineSmall
            )

            Divider(thickness = 1.dp)

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(R.string.ligar),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(R.string.mensagem),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Divider(thickness = 1.dp)

            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(R.string.informacoes),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "${state.name}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(R.string.nome_completo),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = state.phone,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = stringResource(id = R.string.telefone),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = state.cpf,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = stringResource(id = R.string.cpf),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = state.uf,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = stringResource(id = R.string.uf),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )

                state.birthday?.let {
                    Text(
                        text = it.convertToString(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.aniversario),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }
    }
}

@Composable
fun ContactDetailsAppBar(
    onClickBack: () -> Unit,
    onClickRemove: () -> Unit,
    onClickEdit: () -> Unit
) {

    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
        actions =
        {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = onClickBack
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.voltar)
                    )
                }

                Row {
                    IconButton(
                        onClick = onClickEdit
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            tint = Color.White,
                            contentDescription = stringResource(R.string.editar)
                        )
                    }

                    IconButton(onClick = { onClickRemove() }) {
                        Icon(
                            Icons.Default.Delete,
                            tint = Color.White,
                            contentDescription = stringResource(R.string.deletar)
                        )
                    }
                }
            }
        }
    )

}


@Preview
@Composable
fun DetalhesContatoScreenPreview() {
    SmarterAgendaTheme {
        ContactDetailsScreen(state = ContactDetailsUiState())
    }
}