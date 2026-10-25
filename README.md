# TaskFlow ESGI 4AL

Projet pédagogique Android en Kotlin et Jetpack Compose. Le dépôt suit les séances communes des 26–28 octobre 2026 : projet, layouts, listes, état, formulaires, navigation, MVVM et repository mémoire.

## Environnement

- Android Studio récent, Android SDK Platform 36
- Java 17
- Gradle 8.13 (version utilisée par le projet)

```sh
gradle assembleDebug
```

Le wrapper Gradle standard n'a pas pu être généré dans cet environnement : aucun Gradle local n'est installé et l'accès réseau à `services.gradle.org` échoue en résolution DNS. Voir [docs/VALIDATION.md](docs/VALIDATION.md).

La branche `main` contient le squelette. Les corrections sont sur `correction/feature-01` à `correction/feature-08`, chacune issue de la précédente. La carte du cours et les neuf fiches sont disponibles après `git switch correction/feature-08`.
