plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.cryptolist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cryptolist"
        minSdk = 24
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
    //Jetpack Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.fragment.ktx)
    //Fragment
    implementation(libs.fragment.ktx)
    //ViewPager2
    implementation(libs.androidx.viewpager2)
    //Material Design
    implementation(libs.material)
    //KOIN
    implementation(libs.koin.android)
    //MVVM
    implementation(libs.androidx.core.ktx.v1120)
    //GSON
    implementation(libs.gson)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Glide
    implementation(libs.google.material.v1110)
    implementation(libs.glide)
    implementation(libs.androidx.activity.v182)
    annotationProcessor(libs.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}