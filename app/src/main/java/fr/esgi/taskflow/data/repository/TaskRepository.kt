package fr.esgi.taskflow.data.repository

import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    val tasks: StateFlow<List<Task>>
    fun addTask(title: String, description: String, important: Boolean)
    fun setCompleted(taskId: Int, completed: Boolean)
    fun setImportant(taskId: Int, important: Boolean)
}
