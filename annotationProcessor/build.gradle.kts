import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    compile(kotlin("stdlib"))
    implementation("com.google.auto.service:auto-service:1.0-rc6")
    kapt("com.google.auto.service:auto-service:1.0-rc6")

    implementation("org.redundent:kotlin-xml-builder:1.6.0")
}