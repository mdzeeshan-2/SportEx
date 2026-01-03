plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.shan.sportex"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.shan.sportex"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        compose = true   // 👈 IMPORTANT
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"   // Android Studio may suggest higher, accept it
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // --------------------------
    // Core Android libraries
    // --------------------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)

    // --------------------------
    // Jetpack Compose BOM
    // --------------------------
    implementation(platform(libs.androidx.compose.bom))

    // Compose UI
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Compose Material
    implementation(libs.androidx.compose.material)

    // Compose Activity
    implementation(libs.androidx.activity.compose)

    // --------------------------
    // Navigation for Compose
    // --------------------------
    implementation(libs.androidx.navigation.compose)

    // --------------------------
    // ViewModel + Lifecycle
    // --------------------------
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // --------------------------
    // Coil Image Loader (Compose)
    // --------------------------
    implementation(libs.coil.compose)

    // --------------------------
    // Coroutines
    // --------------------------
    implementation(libs.kotlinx.coroutines.android)

    //icons
    implementation(libs.androidx.compose.material.icons.extended)
    // --------------------------
    // Testing
    // --------------------------
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.androidx.navigation.compose)
// ViewModel + Lifecycle
    // --------------------------
    implementation(libs.androidx.lifecycle.viewmodel.compose) // Correctly added
    implementation(libs.androidx.lifecycle.runtime.ktx)
}
