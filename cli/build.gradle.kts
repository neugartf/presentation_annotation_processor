plugins {
    application
    kotlin("jvm")
    kotlin("kapt")
}

application {
    mainClassName = "Main.kt"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile(project(":annotationProcessor"))
    kapt(project(":annotationProcessor"))
}
