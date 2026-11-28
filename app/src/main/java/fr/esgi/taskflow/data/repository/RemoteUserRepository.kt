package fr.esgi.taskflow.data.repository

import fr.esgi.taskflow.data.network.dto.toDomainOrNull
import fr.esgi.taskflow.data.network.services.UserApiService
import fr.esgi.taskflow.domain.model.User
import fr.esgi.taskflow.domain.repositories.UserRepository

class RemoteUserRepository(
    private val userService: UserApiService
): UserRepository {


    override suspend fun getUsers(): List<User> =
        userService.getAllUsers().mapNotNull {
            it.toDomainOrNull()
        }
}