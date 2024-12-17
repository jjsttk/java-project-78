plugins {
    application
    checkstyle
    jacoco
    id("io.freefair.lombok") version "8.11"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.3")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "hexlet.code.App"
}