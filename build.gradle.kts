plugins {
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.spring") version "1.6.21" apply false
    kotlin("plugin.jpa") version "1.6.21" apply false
    id("org.springframework.boot") version "2.7.3" apply false
    id("io.spring.dependency-management") version "1.0.13.RELEASE" apply false
}

allprojects {
    group = "com.example"
    version = "0.2"

    repositories {
        mavenCentral()
    }

    task("introduce yourself").doLast {
        println("Hello, it is ${project.name}")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}