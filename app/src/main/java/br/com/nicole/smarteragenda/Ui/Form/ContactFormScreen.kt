package br.com.nicole.smarteragenda.Ui.Form

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nicole.smarteragenda.Extensions.CpfVisualTransformation
import br.com.nicole.smarteragenda.R
import br.com.nicole.smarteragenda.Theme.SmarterAgendaTheme
import br.com.nicole.smarteragenda.Ui.Components.dialogBoxDate
import br.com.nicole.smarteragenda.Ui.Components.dialogBoxImage
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactFormScreen(
    state: ContactFormUiState,
    modifier: Modifier = Modifier,
    onClickSalvar: () -> Unit = {},
    onCarregarImagem: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val focoAtual = LocalFocusManager.current

    Scaffold(
        topBar = {
            state.appbarTitle?.let { title ->
                ContactFormAppBar(stringResource(id = title))
            }
        },

        ) { paddingValues ->

        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(ScrollState(0))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .clickable {
                            state.onMostrarCaixaDialogoImagem(true)
                        },
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.profilePic).build(),
                    placeholder = painterResource(R.drawable.default_profile_picture),
                    error = painterResource(R.drawable.default_profile_picture),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(id = R.string.foto_perfil_contato),
                )
                Text(
                    text = stringResource(R.string.adicionar_foto),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .weight(2f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    value = state.name,
                    onValueChange = state.onNameChanged,
                    label = { Text(stringResource(id = R.string.nome)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.moveFocus(FocusDirection.Next) }))
                )

                // o telefone ainda não é variável - verificar
                /*OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null
                        )
                    },
                    value = state.phone,
                    onValueChange = state.onPhoneChanged,
                    label = { Text(stringResource(id = R.string.telefone)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.clearFocus() }))
                )

                 */

                // Adicionar dinamicamente campos de telefone
                state.phones.forEachIndexed { index, phone ->
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null
                            )
                        },
                        value = phone,
                        onValueChange = { state.onPhoneChanged(index, it) },
                        label = { Text(stringResource(id = R.string.telefone)) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = (KeyboardActions(onNext = { focoAtual.clearFocus() })),
                        trailingIcon = {
                            if (state.phones.size > 1) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    modifier = Modifier.clickable {
                                        state.onRemovePhone(index)
                                    }
                                )
                            }
                        },
                    )
                }

                OutlinedButton(
                    onClick = { state.onAddPhone() },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        Modifier.padding(8.dp)
                    )
                    Text(text = stringResource(R.string.adicionar_telefone))
                }



                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = null
                        )
                    },
                    value = state.cpf,
                    onValueChange = state.onCpfChanged,
                    label = { Text(stringResource(id = R.string.cpf)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.moveFocus(FocusDirection.Next) })),
                    visualTransformation = CpfVisualTransformation()
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null
                        )
                    },
                    value = state.uf,
                    onValueChange = state.onUfChanged,
                    label = { Text(stringResource(id = R.string.uf)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.moveFocus(FocusDirection.Next) }))
                )

                OutlinedButton(
                    onClick = { state.onMostrarCaixaDialogoData(true) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        Modifier.padding(8.dp)
                    )
                    Text(text = state.birthdayText)
                }


                Spacer(Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp),
                    onClick = {
                        when {
                            state.isCpfRequired() && state.cpf.isBlank() -> {
                                Toast.makeText(context, "CPF é obrigatório para o estado de SP", Toast.LENGTH_SHORT).show()
                            }
                            state.uf.uppercase() == "MG" && state.isUnderage() -> {
                                Toast.makeText(context, "Contatos menores de 18 anos não podem ser cadastrados", Toast.LENGTH_SHORT).show()
                            }
                            state.isCpfRequired() && !state.isValidCpf(state.cpf) -> {
                                Toast.makeText(context, "CPF inválido", Toast.LENGTH_SHORT).show()
                            }
                            else -> onClickSalvar()
                        }
                    },
                ) {
                    Text(text = stringResource(R.string.salvar))
                }


                if (state.mostrarCaixaDialogoImagem) {
                    dialogBoxImage(
                        state.profilePic,
                        onProfilePicChanged = state.onProfilePicChanged,
                        onClickDismiss = { state.onMostrarCaixaDialogoImagem(false) },
                        onClickSave = { onCarregarImagem(it) }
                    )
                }

                if (state.mostrarCaixaDialogoData) {
                    dialogBoxDate(
                        LocalContext.current,
                        currentDate = state.birthday,
                        onClickDismiss = { state.onMostrarCaixaDialogoData(false) },
                        onClickSelectedDate = state.onBirthdayChanged
                    )
                }
            }
        }


    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContactFormAppBar(tituloApprBar: String) {
        TopAppBar(
            title = { Text(text = tituloApprBar, color = Color.White) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            )
        )
    }

    @Preview
    @Composable
    fun FormularioContatoTelaPreview() {
        SmarterAgendaTheme {
            ContactFormScreen(
                state = ContactFormUiState()
            )
        }
    }
