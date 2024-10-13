import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.starter.easylauncher") version "6.4.0"
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
}

val properties = gradleLocalProperties(rootDir, providers)

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
            buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")
            buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

apply(from = "$projectDir/gradle/easylauncher.gradle")

dependencies {

    implementation(project(":core"))

    // Retrofit
    implementation(libs.retrofit)

    implementation(libs.logging.interceptor)

    implementation(libs.sandwich.retrofit)

    // Moshi
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.room.compiler)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.room.compiler)

    implementation("io.github.raamcosta.compose-destinations:core:1.11.7")
    kapt("io.github.raamcosta.compose-destinations:ksp:1.11.7")

    // Navigation compose
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.timber)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}