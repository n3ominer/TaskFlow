package fr.esgi.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.esgi.taskflow.ui.detail.TaskDetailScreen
import fr.esgi.taskflow.ui.home.HomeScreen
import fr.esgi.taskflow.ui.login.LoginScreen
import fr.esgi.taskflow.ui.tasks.CreateTaskScreen
import fr.esgi.taskflow.ui.tasks.TaskListScreen
import fr.esgi.taskflow.ui.tasks.TaskViewModel

@Composable
fun TaskFlowApp() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()
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
            LoginScreen(onLogin = {
                navController.navigate(Destinations.ALL) {
                    popUpTo(Destinations.LOGIN) { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
        composable(Destinations.ALL) {
            HomeScreen(selectedTab = HomeTab.ALL, onSelectTab = selectTab) {
                TaskListScreen(tasks, openTask, taskViewModel::setCompleted, taskViewModel::setImportant)
            }
        }
        composable(Destinations.CREATE) {
            HomeScreen(selectedTab = HomeTab.CREATE, onSelectTab = selectTab) {
                CreateTaskScreen(onCreateTask = { title, description, important ->
                    taskViewModel.addTask(title, description, important)
                    selectTab(HomeTab.ALL)
                })
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
