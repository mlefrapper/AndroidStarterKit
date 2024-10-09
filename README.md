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
This project uses Spotless to ensure consistent code style and formatting. Spotless is a Gradle plugin that automatically formats your code according to predefined rules.

**Running spotless**
You can run Spotless to format your code using the following Gradle command:

bash ./gradlew spotlessApply

This will apply the formatting rules defined in the 'spotless' block.

## Gradle plugins
This project utilizes the following Gradle plugins:
* **easylauncher-gradle-plugin:** This plugin simplifies the creation and management of adaptive launcher icons for Android. It automates the generation of different icon sizes and densities required for various launcher configurations.