package fr.esgi.taskflow.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.esgi.taskflow.data.repository.InMemoryTaskRepository
import fr.esgi.taskflow.data.repository.TaskRepository
import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update

data class TaskUiState(val tasks: List<Task> = emptyList())

class TaskViewModel(
    private val repository: TaskRepository = InMemoryTaskRepository(),
) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState(tasks = repository.tasks.value))
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.tasks.collect { tasks -> _uiState.update { it.copy(tasks = tasks) } }
        }
    }

    fun addTask(title: String, description: String, important: Boolean) {
        val cleanTitle = title.trim()
        if (cleanTitle.isEmpty()) return
        repository.addTask(cleanTitle, description, important)
    }

    fun setCompleted(taskId: Int, completed: Boolean) {
        repository.setCompleted(taskId, completed)
    }

    fun setImportant(taskId: Int, important: Boolean) {
        repository.setImportant(taskId, important)
    }
}
