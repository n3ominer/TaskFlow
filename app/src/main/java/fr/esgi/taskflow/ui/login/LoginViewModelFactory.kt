package fr.esgi.taskflow.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.esgi.taskflow.domain.repositories.UserRepository
import fr.esgi.taskflow.domain.usecase.GetUsersUseCase

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(
            getUsersUseCase = GetUsersUseCase(userRepository)
        ) as T
    }
}