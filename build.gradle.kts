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