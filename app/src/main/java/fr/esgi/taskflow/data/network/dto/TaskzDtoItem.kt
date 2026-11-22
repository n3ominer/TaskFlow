package fr.esgi.taskflow.data.network.dto

import fr.esgi.taskflow.data.network.TaskDto
import fr.esgi.taskflow.domain.model.Task

data class TaskzDtoItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)

/*
  {
    "userId": 1,
    "id": 1,
    "title": "delectus aut autem",
    "completed": false
  },
 */

fun TaskzDtoItem.toDomainOrNull(): Task? {
    val validId = id.takeIf { it > 0 } ?: return null
    val validTitle = title.takeIf { it.isNotBlank() } ?: return null

    return Task(
        id = validId,
        title = validTitle,
        description = "",
        completed = completed,
    )
}
