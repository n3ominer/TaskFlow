# TaskFlow ESGI 4AL

Projet pédagogique Android en Kotlin et Jetpack Compose. Le dépôt suit les séances communes des 26–28 octobre 2026 : projet, layouts, listes, état, formulaires, navigation, MVVM et repository mémoire.

## Environnement

- Android Studio récent, Android SDK Platform 37
- Java 17
- Gradle via le wrapper fourni dans ce dépôt

```sh
./gradlew assembleDebug
```

Le fichier `gradle.properties` active AndroidX pour toutes les branches de correction. Le projet utilise `compileSdk 37`, AGP 9.3.1, Gradle 9.6.0 et le BOM Compose 2026.09.00. Les sorties Gradle et Android Studio sont exclues par `.gitignore`. Voir [docs/VALIDATION.md](docs/VALIDATION.md) pour les résultats de build.

La branche `main` contient le squelette. Les corrections sont sur `correction/feature-01` à `correction/feature-08`, chacune issue de la précédente. La branche 08 contient [la carte du cours](docs/COURSE_MAP.md) et les neuf fiches de séance. Pour dérouler les corrections :

```sh
git switch correction/feature-08
```
