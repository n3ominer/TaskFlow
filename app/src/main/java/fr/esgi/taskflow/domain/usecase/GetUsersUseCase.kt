package fr.esgi.taskflow.domain.usecase

import fr.esgi.taskflow.domain.model.User
import fr.esgi.taskflow.domain.repositories.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(): List<User> = repository.getUsers()
}