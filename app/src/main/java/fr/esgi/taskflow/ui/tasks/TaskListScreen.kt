package fr.esgi.taskflow.ui.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.esgi.taskflow.domain.model.Task
import fr.esgi.taskflow.ui.components.ProgressSummary
import fr.esgi.taskflow.ui.components.TaskCard

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onOpenTask: (Int) -> Unit,
    onSetCompleted: (Int, Boolean) -> Unit,
    onSetImportant: (Int, Boolean) -> Unit,
    onCreateTask: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item { ProgressSummary(tasks) }
        if (tasks.isEmpty()) {
            item { EmptyTasks(onCreateTask) }
        } else {
            items(tasks, key = { it.id }) { task ->
                TaskCard(
                    task = task,
                    onOpen = { onOpenTask(task.id) },
                    onSetCompleted = { onSetCompleted(task.id, it) },
                    onToggleImportant = { onSetImportant(task.id, !task.important) },
                )
            }
        }
    }
}

@Composable
private fun EmptyTasks(onCreateTask: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier.padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Text("Tout commence ici", style = MaterialTheme.typography.titleLarge)
            Text("Ajoutez une tâche pour avancer à votre rythme.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Button(onClick = onCreateTask) {
                Icon(Icons.Default.Add, contentDescription = null)
                Text("Créer une tâche", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
