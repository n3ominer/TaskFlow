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

La branche `main` contient le squelette. Les corrections sont sur `correction/feature-01` à `correction/feature-09`, chacune issue de la précédente. La séance 9 reste le QCM; `correction/feature-09` apporte le style Compose en séance 10. La branche 09 contient [la carte du cours](docs/COURSE_MAP.md) et les fiches de séance. Pour parcourir la dernière correction :

```sh
git switch correction/feature-09
```
