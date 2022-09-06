rootProject.name = "segservices"

include("department-service")
include("employee-service")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val kotlin = version("kotlin", "1.6.21")
            val springBoot = version("spring.boot", "2.7.3")
            val springDependencyManagement = version("spring.dependencyManagement", "1.0.13.RELEASE")

            plugin("kotlin.jvm", "org.jetbrains.kotlin.jvm") .versionRef(kotlin)
            plugin("kotlin.spring", "org.jetbrains.kotlin.plugin.spring") .versionRef(kotlin)
            plugin("kotlin.jpa", "org.jetbrains.kotlin.plugin.jpa").versionRef(kotlin)

            plugin("spring.boot", "org.springframework.boot" )
                .versionRef(springBoot)
            plugin("spring.dependencyManagement", "io.spring.dependency-management")
                .versionRef(springDependencyManagement)
        }
    }
}