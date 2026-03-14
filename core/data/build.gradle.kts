plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.effectivemobiletestproject.core.data"
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
    implementation(libs.coroutines.core)
}
