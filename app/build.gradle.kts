plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("kotlin-parcelize")
    id("com.starter.easylauncher") version "6.4.0"
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mlefrapper.androidstarterkit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mlefrapper.androidstarterkit"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {

        }

        all {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://api.rawg.io/api/\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

apply(
    from = "$projectDir/gradle/easylauncher.gradle"
)

dependencies {
    // Features
    api(project(":features:home"))
    api(project(":features:search"))
    api(project(":features:bookmark"))
    api(project(":features:detail"))

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}

kapt {
    correctErrorTypes = true
}