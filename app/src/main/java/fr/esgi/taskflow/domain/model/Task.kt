package fr.esgi.taskflow.domain.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val completed: Boolean = false,
    val important: Boolean = false,
)

/**
 {
    "id": 1234,
    "title": "Todoz",
    "description": "Description",
    "completed": false,
    "important": true
 }
 */