package fr.esgi.taskflow.ui.tasks

import androidx.lifecycle.ViewModel
import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TaskUiState(
    val tasks: List<Task> = listOf(
        Task(1, "Préparer le cours", "Relire le plan de séance"),
        Task(2, "Créer le projet", "Configurer Android Studio", important = true),
        Task(3, "Faire une pause", "Prendre un café"),
    ),
)

class TaskViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    fun addTask(title: String, description: String, important: Boolean) {
        val cleanTitle = title.trim()
        if (cleanTitle.isEmpty()) return
        _uiState.update { state ->
            val nextId = (state.tasks.maxOfOrNull { it.id } ?: 0) + 1
            state.copy(tasks = state.tasks + Task(nextId, cleanTitle, description.trim(), important = important))
        }
    }

    fun setCompleted(taskId: Int, completed: Boolean) {
        _uiState.update { state ->
            state.copy(tasks = state.tasks.map { task ->
                if (task.id == taskId) task.copy(completed = completed) else task
            })
        }
    }

    fun setImportant(taskId: Int, important: Boolean) {
        _uiState.update { state ->
            state.copy(tasks = state.tasks.map { task ->
                if (task.id == taskId) task.copy(important = important) else task
            })
        }
    }
}
