plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "com.minki.validatordsl.MainKt"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.markodevcic.kvalidation:KValidation:1.0.0")
}