import com.android.build.api.dsl.ProductFlavor

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
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

    flavorDimensions += "environment"

    productFlavors {
        val baseUrlName = "BASE_URL"

        fun ProductFlavor.buildConfigStringField(name: String, value: String) {
            buildConfigField(
                type = "String",
                name = name,
                value = value
            )
        }

        register("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"

            buildConfigStringField(
                name = baseUrlName,
                value = "\"https://api.rawg.io/api/\""
            )
        }
        register("prod") {
            dimension = "environment"
            buildConfigStringField(
                name = baseUrlName,
                value = "\"https://api.rawg.io/api/\""
            )
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
            applicationIdSuffix = ".debug"
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
    api(project(":features:videoplayer"))

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Flipper
    debugImplementation(libs.flipper)
    debugImplementation(libs.soloader)

    debugImplementation(libs.flipper.network.plugin)
    releaseImplementation(libs.flipper.noop)
}

kapt {
    correctErrorTypes = true
}