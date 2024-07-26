rootProject.name = "event-manager"

pluginManagement {
    val versionKotlin: String by settings
    val versionSpringBoot: String by settings
    val versionDependencyManagement: String by settings
    val versionOpenApiGenerator: String by settings

    plugins {
        kotlin("jvm") version versionKotlin
        kotlin("plugin.spring") version versionKotlin
        id("org.springframework.boot") version versionSpringBoot
        id("io.spring.dependency-management") version versionDependencyManagement
        id("org.openapi.generator") version versionOpenApiGenerator
    }
}
