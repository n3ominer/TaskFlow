package fr.esgi.taskflow.ui.theme

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.esgi.taskflow.domain.model.Task
import fr.esgi.taskflow.navigation.HomeTab
import fr.esgi.taskflow.ui.home.HomeScreen
import fr.esgi.taskflow.ui.tasks.TaskListScreen

private val previewTasks = listOf(
    Task(1, "Préparer le cours Compose", "Créer les exemples pour la classe"),
    Task(2, "Réviser la navigation", "Login, onglets et détail", important = true),
    Task(3, "Lancer le projet", "Premier build réussi", completed = true),
)

@Composable
private fun HomePreviewContent() {
    TaskFlowTheme {
        HomeScreen(selectedTab = HomeTab.ALL, taskCount = previewTasks.size, onSelectTab = {}) {
            TaskListScreen(
                tasks = previewTasks,
                onOpenTask = {},
                onSetCompleted = { _, _ -> },
                onSetImportant = { _, _ -> },
                onCreateTask = {},
            )
        }
    }
}

@Preview(name = "Accueil clair", showBackground = true, widthDp = 390, heightDp = 800)
@Composable
private fun LightHomePreview() = HomePreviewContent()

@Preview(
    name = "Accueil sombre",
    showBackground = true,
    widthDp = 390,
    heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun DarkHomePreview() = HomePreviewContent()
