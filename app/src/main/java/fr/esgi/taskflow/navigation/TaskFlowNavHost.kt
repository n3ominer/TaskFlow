package fr.esgi.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.esgi.taskflow.data.network.ApiClient
import fr.esgi.taskflow.data.repository.InMemoryTaskRepository
import fr.esgi.taskflow.data.repository.RemoteUserRepository
import fr.esgi.taskflow.data.repository.TaskRepoSource
import fr.esgi.taskflow.data.repository.TasksRepositoryFactory
import fr.esgi.taskflow.domain.model.User
import fr.esgi.taskflow.domain.usecase.GetUsersUseCase
import fr.esgi.taskflow.ui.detail.TaskDetailScreen
import fr.esgi.taskflow.ui.home.HomeScreen
import fr.esgi.taskflow.ui.login.LoginScreen
import fr.esgi.taskflow.ui.login.LoginViewModel
import fr.esgi.taskflow.ui.login.LoginViewModelFactory
import fr.esgi.taskflow.ui.tasks.CreateTaskScreen
import fr.esgi.taskflow.ui.tasks.TaskListScreen
import fr.esgi.taskflow.ui.tasks.TaskViewModel
import fr.esgi.taskflow.ui.tasks.TaskViewModelFactory

@Composable
fun TaskFlowNavHost() {
    val navController = rememberNavController()

    val taskRepository = remember { TasksRepositoryFactory.create(TaskRepoSource.REMOTE) }

    val taskViewModel: TaskViewModel = viewModel(
        factory = remember(taskRepository) {
            TaskViewModelFactory(taskRepository)
        }
    )

    val userRepository = remember { RemoteUserRepository(ApiClient.createUserApiService()) }
    val loginViewModel: LoginViewModel = viewModel(
        factory = remember(userRepository) { LoginViewModelFactory(userRepository) }
    )

    var selectedUser by remember { mutableStateOf<User?>(null) }


    val tasks = taskViewModel.uiState.collectAsStateWithLifecycle().value.tasks


    val openTask: (Int) -> Unit = { taskId -> navController.navigate(Destinations.detail(taskId)) }
    val selectTab: (HomeTab) -> Unit = { tab ->
        navController.navigate(tab.route) {
            popUpTo(Destinations.ALL) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(navController = navController, startDestination = Destinations.LOGIN) {
        composable(Destinations.LOGIN) {
            LoginScreen(
                viewModel = loginViewModel,
                onLogin = { user ->
                    selectedUser = user
                    navController.navigate(Destinations.ALL) {
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Destinations.ALL) {
            LaunchedEffect(
                selectedUser?.id
            ) {
                selectedUser?.let { taskViewModel.getAllTodos(it.id) }
            }

            // taskViewModel.getAllTodos() A NE PAS FAIRE

            HomeScreen(selectedTab = HomeTab.ALL, onSelectTab = selectTab) {
                TaskListScreen(
                    tasks,
                    openTask,
                    taskViewModel::setCompleted,
                    taskViewModel::setImportant
                )
            }
        }
        composable(Destinations.CREATE) {
            HomeScreen(selectedTab = HomeTab.CREATE, onSelectTab = selectTab) {
                CreateTaskScreen(
                    onCreateTask = { title, description, important ->
                        taskViewModel.addTask(title, description, important)
                        selectTab(HomeTab.ALL)
                    }
                )
            }
        }
        composable(Destinations.IMPORTANT) {
            HomeScreen(selectedTab = HomeTab.IMPORTANT, onSelectTab = selectTab) {
                TaskListScreen(tasks.filter { it.important }, openTask, taskViewModel::setCompleted, taskViewModel::setImportant)
            }
        }
        composable(
            route = Destinations.DETAIL,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType }),
        ) { entry ->
            val taskId = entry.arguments?.getInt("taskId")
            TaskDetailScreen(
                task = tasks.firstOrNull { it.id == taskId },
                onBack = { navController.popBackStack() },
            )
        }
    }
}
