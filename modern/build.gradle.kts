plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
    application
    id("com.jfrog.bintray")
}

ext["moduleName"] = "rs.emulate.modern"

val bouncycastleVersion: String by project
val guavaVersion: String by project
val nettyVersion: String by project
val jimfsVersion: String by project

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.google.guava", "guava", guavaVersion)
    implementation("org.bouncycastle", "bcprov-jdk15on", bouncycastleVersion)

    testImplementation(project(":common", "testRuntime"))
    testImplementation("com.google.jimfs", "jimfs", jimfsVersion)

    api(project(":common"))
    api(project(":util"))
}

application {
    mainClassName = "rs.emulate.modern.CacheKt"
}

