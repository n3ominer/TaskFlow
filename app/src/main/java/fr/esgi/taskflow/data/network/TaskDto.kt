package fr.esgi.taskflow.data.network

import fr.esgi.taskflow.domain.model.Task

data class TaskDto(
    val id: Int?,
    val title: String?,
    val completed: Boolean?
)

// TaskDto -> Task
fun TaskDto.toDomainOrNull(): Task? {
    val validId = id?.takeIf { it > 0 } ?: return null
    val validTitle = title?.takeIf { it.isNotBlank() } ?: return null

    return Task(
        validId,
        validTitle,
        description = "",
        completed = completed ?: false
    )
}
