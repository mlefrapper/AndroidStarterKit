plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mlefrapper.androidstarterkit.videoplayer"
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(project(":ui"))
    api(project(":core"))
    api(project(":data"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.exoplayer)
}