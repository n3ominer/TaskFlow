package fr.esgi.taskflow.data.repository

import fr.esgi.taskflow.domain.repositories.TaskRepository
import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.plus

// MOCk -> false data
class InMemoryTaskRepository: TaskRepository {
    // State spécifique à la liste de Task
    // Il est observé par le ViewModel, pour mettre à jour cette donné spécifique
    // dans son UiState global
    private val _tasks = MutableStateFlow(
        listOf(
            Task(userId = 10, 1, "Préparer le cours", "Relire le plan de séance"),
            Task(userId = 10, 2, "Créer le projet", "Configurer Android Studio", important = true),
            Task(userId = 10, 3, "Faire une pause", "Prendre un café"),
        )
    )

    override val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    override suspend fun refresh(userId: Int) = Unit

    override fun addTask(
        title: String,
        description: String,
        important: Boolean
    ) {
        val cleanTitle = title.trim()
        if (cleanTitle.isEmpty()) return
        _tasks.update { state ->
            val nextId = (state.maxOfOrNull { it.id } ?: 0) + 1
            state + Task(
                userId = 10,
                id = nextId,
                title = cleanTitle,
                description = description.trim(),
                important = important
            )
        }
    }

    override fun setCompleted(taskId: Int, completed: Boolean) {
        _tasks.update { current ->
            // EMIT EVENT
            current.map { task ->
                if(task.id == taskId) task.copy(completed = completed)
                else task
            }
        }
    }

    override fun setImportant(taskId: Int, important: Boolean) {
        _tasks.update { current ->
            current.map { task ->
                if(task.id == taskId) task.copy(important = important)
                else task
            }

        }
    }

    // synchronize les tasks

    // TACHE A
    // TACHE B
    // TACHE C

}