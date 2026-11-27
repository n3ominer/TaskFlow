package fr.esgi.taskflow.data.repository

import fr.esgi.taskflow.data.TaskRepository
import fr.esgi.taskflow.data.network.TaskApiService
import fr.esgi.taskflow.data.network.toDomainOrNull
import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RemoteTaskRepository(private val taskService: TaskApiService): TaskRepository {
    private var remoteTasks: List<Task> = emptyList() // --> Liste des tasks qui viennent de l'API
    private var sessionTask: List<Task> = emptyList() // --> Liste des tasks qui sont locales

    private var completedOverride: Map<Int, Boolean> = emptyMap()
    private var importantOverride: Map<Int, Boolean> = emptyMap()
    // State privé, observable et modifiable localement uniquement
    private val _task = MutableStateFlow<List<Task>>(emptyList())

    // State publique et observable depuis l'exterieur
    override val tasks: StateFlow<List<Task>> = _task.asStateFlow()

    // La fonction qui va nous permettre de faire l'appel HTTP GET vers le endpoint dans le service
    override suspend fun refresh() {
        val fetched = taskService.getAllTodos().mapNotNull { it.toDomainOrNull() }
        remoteTasks = fetched

        // Mettre à jour l'état et notifier pour recomposition
        publish()
    }
    // Modification locale
    override fun addTask(
        title: String,
        description: String,
        important: Boolean
    ) {
        // Logique d'ajout
        val cleanTitle = title.trim()
        if(cleanTitle.isEmpty()) return
        // -10 -> -11
        val nextId = (sessionTask.minOfOrNull { it.id } ?: 0) - 1
        sessionTask = sessionTask + Task(
            nextId,
            cleanTitle,
            description.trim(),
            important = important
        )
        // Mettre à jour l'état et notifier pour recomposition
        publish()
    }

    // Modification locale
    override fun setCompleted(taskId: Int, completed: Boolean) {
        // Logique de completion
        if(_task.value.none { it.id == taskId }) return
        /*
            [ (k -> v),(k1 -> v1), (kn -> vn) ]
         */
                            // [..., (kn, vn)] + ("123": true)
        completedOverride = completedOverride + (taskId to completed)
        // Mettre à jour l'état et notifier pour recomposition
        publish()
    }

    // Modification locale
    override fun setImportant(taskId: Int, important: Boolean) {
        // Logique d'ajout
        if(_task.value.none { it.id == taskId}) return
        // Mettre à jour le l'importance d'une Todoz
        importantOverride = importantOverride + (taskId to important)
        // Mettre à jour l'état et notifier pour recomposition
        publish()
    }

    private fun publish() {
        // La todoz doit garder l'état de completion du serveur ou le nouvel etat local
        // La todoz doit garder l'état d'importance du serveur ou le nouvel etat local
        _task.value = (sessionTask + remoteTasks).map { task ->
            // task1 -> copy -> task1' (avec touts les paramètre de task1)
            task.copy(
                completed = completedOverride[task.id] ?: task.completed,
                important = importantOverride[task.id] ?: task.important
            )
        }
    }
}