package fr.esgi.taskflow.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.esgi.taskflow.domain.repositories.TaskRepository
import fr.esgi.taskflow.domain.usecase.AddTaskUseCase
import fr.esgi.taskflow.domain.usecase.GetAllTodosUseCase
import fr.esgi.taskflow.domain.usecase.ObserveTasksUseCase
import fr.esgi.taskflow.domain.usecase.SetCompletedTaskUseCase
import fr.esgi.taskflow.domain.usecase.SetImportantTasksUseCase

@Suppress("UNCHECKED_CAST")
class TaskViewModelFactory(private val repository: TaskRepository)
    : ViewModelProvider.Factory {

    // Doit savoir créer un Viewmodel
    // Pour cela elle a besoin de:
    //  - Repository
    // - Savoir créer chaque usecase
    // - Il faut créer un ViewModel quand il le faut
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // C'est pour checker qu'on va créer une instance du bon type
        require(modelClass.isAssignableFrom(TaskViewModel::class.java))
        return TaskViewModel(
            observeTasksUseCase = ObserveTasksUseCase(repository),
            addTaskUseCase = AddTaskUseCase(repository),
            setCompletedTaskUseCase = SetCompletedTaskUseCase(repository),
            setImportantTasksUseCase = SetImportantTasksUseCase(repository),
            getAllTodosUseCase = GetAllTodosUseCase(repository)
        ) as T
    }
}