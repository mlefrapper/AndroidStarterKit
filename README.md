# AndroidStarterKit

## Tech stack & open-source libraries
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based
- [Compose](https://developer.android.com/jetpack/compose): Modern toolkit for building native UI
- [Hilt](https://dagger.dev/hilt/): for dependency injection.
- [Timber](https://github.com/JakeWharton/timber): A logger with a small, extensible API.

## Architecture

## Getting started

### Installation

## Code style and formatting (spotless)

**Running spotless**


This will apply the formatting rules defined in the 'spotless' block.

## Gradle plugins
This project utilizes the following Gradle plugins:

* **spotless:** Spotless is a Gradle plugin that automatically formats your code according to predefined rules. You can run Spotless to format your code using the following Gradle command:
`./gradlew spotlessApply`

* **easylauncher-gradle-plugin:** This plugin simplifies the creation and management of adaptive launcher icons for Android. It automates the generation of different icon sizes and densities required for various launcher configurations.