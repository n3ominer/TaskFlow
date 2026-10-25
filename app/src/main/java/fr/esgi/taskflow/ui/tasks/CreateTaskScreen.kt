package fr.esgi.taskflow.ui.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateTaskScreen(onCreateTask: (String, String, Boolean) -> Unit) {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val important = remember { mutableStateOf(false) }
    val titleError = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        OutlinedTextField(
            value = title.value,
            onValueChange = { title.value = it; titleError.value = false },
            label = { Text("Titre") },
            isError = titleError.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
        )
        androidx.compose.foundation.layout.Row {
            Checkbox(checked = important.value, onCheckedChange = { important.value = it })
            Text("Tâche importante", modifier = Modifier.padding(top = 14.dp))
        }
        if (titleError.value) Text("Le titre est obligatoire", color = MaterialTheme.colorScheme.error)
        Button(onClick = {
            val cleanTitle = title.value.trim()
            if (cleanTitle.isEmpty()) titleError.value = true
            else {
                onCreateTask(cleanTitle, description.value.trim(), important.value)
                title.value = ""
                description.value = ""
                important.value = false
            }
        }) { Text("Créer la tâche") }
    }
}
