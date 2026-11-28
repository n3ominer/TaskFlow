package fr.esgi.taskflow.data.repository

import fr.esgi.taskflow.domain.repositories.TaskRepository
import fr.esgi.taskflow.data.network.ApiClient

enum class TaskRepoSource { LOCAL, REMOTE }

object TasksRepositoryFactory {
    fun create(source: TaskRepoSource): TaskRepository = when (source) {
        TaskRepoSource.LOCAL -> InMemoryTaskRepository()
        TaskRepoSource.REMOTE -> RemoteTaskRepository(
            ApiClient.createTaskApiService()
        )
    }
}