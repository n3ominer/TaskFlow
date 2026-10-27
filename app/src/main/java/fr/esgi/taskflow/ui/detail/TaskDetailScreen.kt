package fr.esgi.taskflow.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.esgi.taskflow.domain.model.Task

@Composable
fun TaskDetailScreen(task: Task?, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Détail de la tâche", style = MaterialTheme.typography.headlineSmall)
        Text(task?.title ?: "Tâche introuvable")
        if (task != null) {
            Text(task.description.ifBlank { "Aucune description" })
            Text(if (task.important) "★ Importante" else "☆ Ordinaire")
            Text(if (task.completed) "Terminée" else "À faire")
        }
        Button(onClick = onBack) { Text("Retour") }
    }
}
