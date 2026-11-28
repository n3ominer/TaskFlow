package fr.esgi.taskflow.data.network

import fr.esgi.taskflow.domain.model.Task


/*
  {
    "userId": 1,
    "id": 1,
    "title": "delectus aut autem",
    "completed": false
  }
 */
data class TaskDto(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val completed: Boolean?
)

// TaskDto -> Task
fun TaskDto.toDomainOrNull(): Task? {
    val validId = id?.takeIf { it > 0 } ?: return null
    val validTitle = title?.takeIf { it.isNotBlank() } ?: return null

    return Task(
        userId = userId ?: 0,
        id = validId,
        title = validTitle,
        description = "",
        completed = completed ?: false
    )
}
