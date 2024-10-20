// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.spotless.plugin.gradle)
        classpath(libs.hilt.android.gradle.plugin)
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.devtools.ksp") version ("2.0.21-1.0.25") apply false
    id("com.google.dagger.hilt.android") version ("2.52") apply false
    id("com.diffplug.spotless") version "6.19.0" apply false
    id("com.github.ben-manes.versions") version "0.51.0"
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
}

subprojects {
    afterEvaluate {
        project.apply(from = "${project.rootDir}/spotless.gradle")
    }
}