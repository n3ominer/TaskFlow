package fr.esgi.taskflow.domain.repositories

import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    // _tasks --> MutableStateFlow

    suspend fun refresh(userId: Int)

    val tasks: StateFlow<List<Task>>
    fun addTask(title: String, description: String, important: Boolean) // -> Envoi à une API
    fun setCompleted(taskId: Int, completed: Boolean)
    fun setImportant(taskId: Int, important: Boolean)
}