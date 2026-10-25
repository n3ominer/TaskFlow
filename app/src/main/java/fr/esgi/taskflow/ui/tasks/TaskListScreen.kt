package fr.esgi.taskflow.ui.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.esgi.taskflow.domain.model.Task

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onOpenTask: (Int) -> Unit,
    onSetCompleted: (Int, Boolean) -> Unit,
    onSetImportant: (Int, Boolean) -> Unit,
) {
    if (tasks.isEmpty()) {
        Text("Aucune tâche à afficher", modifier = Modifier.padding(24.dp))
        return
    }
    LazyColumn {
        items(tasks, key = { it.id }) { task ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Checkbox(
                        checked = task.completed,
                        onCheckedChange = { onSetCompleted(task.id, it) },
                    )
                    Text(task.title, modifier = Modifier.weight(1f).clickable { onOpenTask(task.id) })
                    TextButton(onClick = { onSetImportant(task.id, !task.important) }) {
                        Text(if (task.important) "★" else "☆")
                    }
                }
                HorizontalDivider()
            }
        }
    }
}
