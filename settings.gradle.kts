rootProject.name = "segservices"

include("department-service")
include("employee-service")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // plugins versions
            val kotlin = version("kotlin", "1.6.21")

            val springBoot = version("spring.boot", "2.7.3")
            val springDependencyManagement = version("spring.dependencyManagement", "1.0.13.RELEASE")

            val openapiGenerator = version("openapi.generator", "6.1.0")

            // libraries versions
            val springfox = version("springfox", "3.0.0")

            val swaggerAnnotations = version("swagger.annotations", "2.2.2")
            val jacksonNullable = version("jackson.nullable", "0.2.3")
            val javaxValidation = version("javax.validation", "2.0.1.Final")
            val springdoc = version("springdoc", "1.6.11")

            val mapstruct = version("mapstruct", "1.5.2.Final")
            val lombokMapstruct = version("lombok.mapstruct", "0.2.0")


            plugin("kotlin.jvm", "org.jetbrains.kotlin.jvm").versionRef(kotlin)
            plugin("kotlin.spring", "org.jetbrains.kotlin.plugin.spring").versionRef(kotlin)
            plugin("kotlin.jpa", "org.jetbrains.kotlin.plugin.jpa").versionRef(kotlin)

            plugin("spring.boot", "org.springframework.boot" ).versionRef(springBoot)
            plugin("spring.dependencyManagement", "io.spring.dependency-management")
                .versionRef(springDependencyManagement)

            plugin("openapi.generator", "org.openapi.generator").versionRef(openapiGenerator)

            library("springfox.bootStarter", "io.springfox", "springfox-boot-starter")
                .versionRef(springfox)
            library("springfox.ui", "io.springfox", "springfox-swagger-ui")
                .versionRef(springfox)
            bundle("springfox",
                listOf("springfox.bootStarter", "springfox.ui")
            )

            library("swagger.annotations", "io.swagger.core.v3", "swagger-annotations")
                .versionRef(swaggerAnnotations)
            library("jackson.nullable", "org.openapitools", "jackson-databind-nullable")
                .versionRef(jacksonNullable)
            library("javax.validation", "javax.validation", "validation-api")
                .versionRef(javaxValidation)
            library("springdoc", "org.springdoc", "springdoc-openapi-ui")
                .versionRef(springdoc)
            bundle("openapi.codegen.util",
                listOf(
                    "swagger.annotations",
                    "jackson.nullable",
                    "javax.validation",
                    "springdoc"
                )
            )

            library("mapstruct", "org.mapstruct", "mapstruct").versionRef(mapstruct)
            library("mapstruct.processor", "org.mapstruct", "mapstruct-processor").versionRef(mapstruct)
            library("lombok.mapstruct", "org.projectlombok", "lombok-mapstruct-binding")
                .versionRef(lombokMapstruct)
            bundle("mapstruct.annotationProcessors",
                listOf("mapstruct.processor", "lombok.mapstruct")
            )
        }
    }
}