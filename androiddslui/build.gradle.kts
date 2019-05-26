plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        applicationId = "com.minki.dslui"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

}

repositories {
    google()
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.0.2")

    val ankoVersion = "0.10.8"
    implementation("org.jetbrains.anko:anko-sdk21:$ankoVersion")
    implementation("org.jetbrains.anko:anko-sdk21-listeners:$ankoVersion")
    implementation(kotlin("stdlib-jdk8:1.3.31"))
}
