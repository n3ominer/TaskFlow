package fr.esgi.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.esgi.taskflow.domain.model.Task

private val sampleTasks = listOf(
    Task(1, "Préparer le cours", "Relire le plan de séance"),
    Task(2, "Créer le projet", "Configurer Android Studio"),
    Task(3, "Faire une pause", "Prendre un café"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text("TaskFlow", style = MaterialTheme.typography.headlineMedium)
                        Text("Organisez votre travail, une tâche à la fois.")
                        Button(onClick = {}) { Text("Nouvelle tâche") }
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(sampleTasks, key = { it.id }) { task ->
                                Text("${task.title} — ${task.description}")
                            }
                        }
                    }
                }
            }
        }
    }
}
