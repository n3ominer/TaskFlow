package fr.esgi.taskflow.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.esgi.taskflow.domain.model.Task
import fr.esgi.taskflow.domain.usecase.AddTaskUseCase
import fr.esgi.taskflow.domain.usecase.GetAllTodosUseCase
import fr.esgi.taskflow.domain.usecase.ObserveTasksUseCase
import fr.esgi.taskflow.domain.usecase.SetCompletedTaskUseCase
import fr.esgi.taskflow.domain.usecase.SetImportantTasksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
)

class TaskViewModel(
    observeTasksUseCase: ObserveTasksUseCase,
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val setCompletedTaskUseCase: SetCompletedTaskUseCase,
    private val setImportantTasksUseCase: SetImportantTasksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState(observeTasksUseCase().value))
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeTasksUseCase().collect { tasks -> _uiState.update { it.copy(tasks = tasks) } }
        }
    }


    fun getAllTodos(userId: Int) {
        // Quel est le user id ?
        viewModelScope.launch {
            getAllTodosUseCase(userId)
        }
    }
    fun addTask(title: String, description: String, important: Boolean) =
        addTaskUseCase(title, description, important)

    fun setCompleted(taskId: Int, completed: Boolean) =
        setCompletedTaskUseCase(taskId, completed)

    fun setImportant(taskId: Int, important: Boolean) =
        setImportantTasksUseCase(taskId, important)
}
