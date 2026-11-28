package fr.esgi.taskflow.domain.repositories

import fr.esgi.taskflow.domain.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>
}