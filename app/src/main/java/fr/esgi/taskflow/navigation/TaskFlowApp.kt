package fr.esgi.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.esgi.taskflow.domain.model.Task
import fr.esgi.taskflow.ui.detail.TaskDetailScreen
import fr.esgi.taskflow.ui.home.HomeScreen
import fr.esgi.taskflow.ui.login.LoginScreen
import fr.esgi.taskflow.ui.tasks.CreateTaskScreen
import fr.esgi.taskflow.ui.tasks.TaskListScreen

@Composable
fun TaskFlowApp() {
    val navController = rememberNavController()
    val tasks = remember {
        mutableStateListOf(
            Task(1, "Préparer le cours", "Relire le plan de séance"),
            Task(2, "Créer le projet", "Configurer Android Studio", important = true),
            Task(3, "Faire une pause", "Prendre un café"),
        )
    }

    val setCompleted: (Int, Boolean) -> Unit = { taskId, completed ->
        val index = tasks.indexOfFirst { it.id == taskId }
        if (index >= 0) tasks[index] = tasks[index].copy(completed = completed)
    }
    val setImportant: (Int, Boolean) -> Unit = { taskId, important ->
        val index = tasks.indexOfFirst { it.id == taskId }
        if (index >= 0) tasks[index] = tasks[index].copy(important = important)
    }
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
                TaskListScreen(tasks, openTask, setCompleted, setImportant)
            }
        }
        composable(Destinations.CREATE) {
            HomeScreen(selectedTab = HomeTab.CREATE, onSelectTab = selectTab) {
                CreateTaskScreen(onCreateTask = { title, description, important ->
                    val nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
                    tasks.add(Task(nextId, title, description, important = important))
                    selectTab(HomeTab.ALL)
                })
            }
        }
        composable(Destinations.IMPORTANT) {
            HomeScreen(selectedTab = HomeTab.IMPORTANT, onSelectTab = selectTab) {
                TaskListScreen(tasks.filter { it.important }, openTask, setCompleted, setImportant)
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
