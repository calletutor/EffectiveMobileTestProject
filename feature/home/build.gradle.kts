plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.effectivemobiletestproject.feature.home"
    compileSdk = 36

    defaultConfig {
        minSdk = 31
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":feature:course"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.coroutines.android)
    implementation(libs.material)
    implementation(libs.constraintlayout)
}


