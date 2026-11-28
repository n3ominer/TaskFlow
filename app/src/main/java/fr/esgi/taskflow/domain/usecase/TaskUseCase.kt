package fr.esgi.taskflow.domain.usecase

import fr.esgi.taskflow.domain.repositories.TaskRepository
import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.StateFlow

// Usecase OBSERVE TASK LIST
class ObserveTasksUseCase(private val  repository: TaskRepository) {
    operator fun invoke(): StateFlow<List<Task>> = repository.tasks
}

class GetAllTodosUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(userId: Int) = repository.refresh(userId)
}

// Usecase CREATION TASK
class AddTaskUseCase(private val repository: TaskRepository) {
    operator fun invoke(title: String, description: String, important: Boolean) {
        val cleanTitle = title.trim()
        if (cleanTitle.isNotEmpty()) {
            repository.addTask(
                cleanTitle,
                description.trim(),
                important
            )
        }
    }
}

// Usecase UPDATE COMPLETED TASK
class SetCompletedTaskUseCase(private val repository: TaskRepository) {
    operator fun invoke(taskId: Int, completed: Boolean) = repository.setCompleted(taskId, completed)

}

// Usecase UPDATE IMPORTANT TASK
class SetImportantTasksUseCase(private val repository: TaskRepository) {
    operator fun invoke(taskId: Int, important: Boolean) = repository.setImportant(taskId, important)
}

// Ysecase DELETE TASK
class DeleteTaskUseCase(){

}
