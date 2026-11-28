package fr.esgi.taskflow.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.esgi.taskflow.domain.model.User
import fr.esgi.taskflow.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val users: List<User> = emptyList(),
    val selectedUser: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)


class LoginViewModel(
    private val getUsersUseCase: GetUsersUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        // Call repository
        viewModelScope.launch {
            // Provoquer un état de chargement dans la vue
            _uiState.update { it.copy(isLoading = true, error = null) }
            // Appel au repo
            runCatching { getUsersUseCase() }
                .onSuccess { users -> _uiState.update { it.copy(users = users, isLoading = false) } }
                .onFailure { err -> _uiState.update { it.copy(error = err.message ?: "Error", isLoading = false) } }

        }
    }

    fun onUserSelected(user: User) {
        _uiState.update { it.copy(selectedUser = user) }
    }
}