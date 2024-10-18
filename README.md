# AndroidStarterKit

## Tech stack & open-source libraries

- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/) based
- [Compose](https://developer.android.com/jetpack/compose): Modern toolkit for building native UI
- [Hilt](https://dagger.dev/hilt/): for dependency injection.
- [Timber](https://github.com/JakeWharton/timber): A logger with a small, extensible API.

## Debugging Tools

This project utilizes Flipper for debugging purposes : 

- **Network Inspection:** Flipper's Network plugin allows you to inspect network requests made by the app, including request and response details, headers, and timing information. This is helpful for debugging network-related issues and understanding API interactions.
- **Database Inspection:** Flipper's Database plugin enables you to browse and query the Room database used by the app. You can view database schemas, tables, and individual records, making it easier to debug data-related problems.

## Architecture

## Getting started

### Installation

## Gradle plugins

This project utilizes the following Gradle plugins:

* **[gradle-versions-plugin](https://github.com/ben-manes/gradle-versions-plugin):** this plugin
  provides a task to determine which dependencies have updates. Additionally, the plugin checks for
  updates to Gradle itself.
  `./gradlew dependencyUpdates`
    * **[spotless](https://github.com/diffplug/spotless):** Spotless is a Gradle plugin that
      automatically formats your code according to predefined rules. You can run Spotless to format
      your code using the following Gradle command:
      `./gradlew spotlessApply`

* **[easylauncher-gradle-plugin](https://github.com/usefulness/easylauncher-gradle-plugin):** This
  plugin simplifies the creation and management of adaptive launcher icons for Android. It automates
  the generation of different icon sizes and densities required for various launcher configurations.