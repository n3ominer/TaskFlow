package fr.esgi.taskflow

import androidx.compose.ui.test.assertExists
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class NavigationUiTest {
    @get:Rule val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loginBottomNavigationAndCreateTask() {
        composeRule.onNodeWithText("E-mail").performTextInput("classe@esgi.fr")
        composeRule.onNodeWithText("Mot de passe").performTextInput("demo")
        composeRule.onNodeWithText("Se connecter").performClick()
        composeRule.onNodeWithText("Toutes les tâches").assertExists()

        composeRule.onNodeWithText("Créer").performClick()
        composeRule.onNodeWithText("Créer une tâche").assertExists()
        composeRule.onNodeWithText("Titre").performTextInput("Réviser la navigation")
        composeRule.onNodeWithText("Créer la tâche").performClick()
        composeRule.onNodeWithText("Réviser la navigation").assertExists()

        composeRule.onNodeWithText("Importantes").performClick()
        composeRule.onNodeWithText("Tâches importantes").assertExists()
    }
}
