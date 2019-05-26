plugins {
    kotlin("jvm")
    application
}

application {
    mainClassName = "com.minki.repl.MainKt"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}