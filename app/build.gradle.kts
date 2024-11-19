plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "ro.simona.userstestproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "ro.simona.userstestproject"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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

dependencies {
    // AndroidX
    implementation(libs.androidx.core.ktx)

    //AndroidX Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Jetpack Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    //Paging
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)

    //UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Koin for Android
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Coroutines
    implementation(libs.bundles.coroutines)

    // Coil
    implementation(libs.bundles.coil)

    //testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}