package fr.esgi.taskflow.data.repository

import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryTaskRepository : TaskRepository {
    private val _tasks = MutableStateFlow(
        listOf(
            Task(1, "Préparer le cours", "Relire le plan de séance"),
            Task(2, "Créer le projet", "Configurer Android Studio", important = true),
            Task(3, "Faire une pause", "Prendre un café"),
        ),
    )
    override val tasks = _tasks.asStateFlow()

    override fun addTask(title: String, description: String, important: Boolean) {
        val cleanTitle = title.trim()
        if (cleanTitle.isEmpty()) return
        _tasks.update { current ->
            val nextId = (current.maxOfOrNull { it.id } ?: 0) + 1
            current + Task(nextId, cleanTitle, description.trim(), important = important)
        }
    }

    override fun setCompleted(taskId: Int, completed: Boolean) {
        _tasks.update { current ->
            current.map { task -> if (task.id == taskId) task.copy(completed = completed) else task }
        }
    }

    override fun setImportant(taskId: Int, important: Boolean) {
        _tasks.update { current ->
            current.map { task -> if (task.id == taskId) task.copy(important = important) else task }
        }
    }
}
