plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "com.minki.htmlbuilder.MainKt"
}

repositories {
    maven("http://dl.bintray.com/kotlin/kotlinx.html/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.1")
}