package fr.esgi.taskflow.navigation

object Destinations {
    const val LOGIN = "login"
    const val HOME = "home"
    const val ALL = "all"
    const val CREATE = "create"
    const val IMPORTANT = "important"
    const val DETAIL = "detail/{taskId}"

    fun detail(taskId: Int) = "detail/$taskId"
}

enum class HomeTab(val route: String, val label: String, val symbol: String) {
    ALL(Destinations.ALL, "Toutes", "☷"),
    CREATE(Destinations.CREATE, "Créer", "+"),
    IMPORTANT(Destinations.IMPORTANT, "Importantes", "★"),
}
