import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("kotlin-parcelize") // needed only for non-primitive classes
}

val properties = gradleLocalProperties(rootDir, providers)

android {
    namespace = "com.mlefrapper.androidstarterkit.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
                name = "API_KEY",
                value = properties.getProperty("API_KEY")
            )
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
        buildConfig = true
    }
}

dependencies {

    // Room
    api(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.room.compiler)
    api(libs.androidx.room.ktx)
    ksp(libs.androidx.room.room.compiler)

    // Retrofit
    api(libs.retrofit)

    // Interceptor
    api(libs.logging.interceptor)

    // Sandwich
    api(libs.sandwich.retrofit)

    // Moshi
    api(libs.moshi)
    api(libs.converter.moshi)
    ksp(libs.moshi.kotlin.codegen)

    api(libs.javax.inject)
    api(libs.kotlinx.serialization.json)
}