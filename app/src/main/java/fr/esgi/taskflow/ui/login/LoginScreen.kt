package fr.esgi.taskflow.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.esgi.taskflow.R
import fr.esgi.taskflow.domain.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLogin: (User) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text("TaskFlow", style = MaterialTheme.typography.headlineLarge)
        Text("Connexion de démonstration", modifier = Modifier.padding(bottom = 24.dp))
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> {
                Text(state.error!!, color = MaterialTheme.colorScheme.error)
                Button(
                    onClick = { viewModel.loadUsers() },
                    modifier = Modifier.padding(top = 8.dp)

                ) { Text(stringResource(R.string.try_again_login_screen_error_text))}
            }
            else -> {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = state.selectedUser?.name ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.user_text_fiel_label)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        state.users.forEach { user ->
                            DropdownMenuItem(
                                text = { Text(user.name) },
                                onClick = {
                                    viewModel.onUserSelected(user)
                                    expanded = false
                                }
                            )
                        }
                    }

                }
            }
        }
        Button(
            onClick = { state.selectedUser?.let { onLogin(it) } },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) { Text("Se connecter") }
    }
}
