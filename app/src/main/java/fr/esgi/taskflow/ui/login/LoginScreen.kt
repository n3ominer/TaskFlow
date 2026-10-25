package fr.esgi.taskflow.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val error = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text("TaskFlow", style = MaterialTheme.typography.headlineLarge)
        Text("Connexion de démonstration", modifier = Modifier.padding(bottom = 24.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it; error.value = false },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it; error.value = false },
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        if (error.value) Text("Saisissez un e-mail et un mot de passe", color = MaterialTheme.colorScheme.error)
        Button(
            onClick = {
                if (email.value.isBlank() || password.value.isBlank()) error.value = true
                else onLogin()
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        ) { Text("Se connecter") }
    }
}
