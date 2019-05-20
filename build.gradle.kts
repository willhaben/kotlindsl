plugins {
    kotlin("jvm") version "1.3.31" apply false
    kotlin("android") version "1.3.31" apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.1")
    }
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}
