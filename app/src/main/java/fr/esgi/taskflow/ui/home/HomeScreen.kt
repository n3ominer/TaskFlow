package fr.esgi.taskflow.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.esgi.taskflow.navigation.HomeTab

@Composable
fun HomeScreen(
    selectedTab: HomeTab,
    onSelectTab: (HomeTab) -> Unit,
    content: @Composable () -> Unit,
) {
    val title = when (selectedTab) {
        HomeTab.ALL -> "Toutes les tâches"
        HomeTab.CREATE -> "Créer une tâche"
        HomeTab.IMPORTANT -> "Tâches importantes"
    }
    Scaffold(
        topBar = {
            Text(
                title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth().padding(20.dp),
            )
        },
        bottomBar = {
            NavigationBar {
                HomeTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = tab == selectedTab,
                        onClick = { onSelectTab(tab) },
                        icon = { Text(tab.symbol) },
                        label = { Text(tab.label) },
                    )
                }
            }
        },
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) { content() }
    }
}
