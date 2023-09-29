@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.shapeide.udarakita"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shapeide.udarakita"
        minSdk = 24
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    implementation(project(mapOf("path" to ":local")))
    implementation(project(mapOf("path" to ":presenter")))
    implementation(project(mapOf("path" to ":model")))
    implementation(project(mapOf("path" to ":ui")))
    implementation(project(mapOf("path" to ":remote")))
    implementation(project(mapOf("path" to ":core")))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}