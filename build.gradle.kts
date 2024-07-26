plugins {
    java
    idea
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.openapi.generator")
}

val groupProject: String by project
val versionProject: String by project
val versionSpringBoot: String by project
val versionLombok: String by project
val versionSwagger: String by project
val versionSwaggerAnnotations: String by project
val versionSpringdocOpenapiUi: String by project
val versionJacksonDatabindNullable: String by project
val versionLogback: String by project
val versionSnakeyaml: String by project

group = groupProject
version = versionProject

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:$versionSpringBoot")
    }
}

sourceSets.main {
    java.srcDirs("build/openapi/src/main/java")
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Swagger
    implementation("io.springfox:springfox-swagger2:$versionSwagger")
    implementation("io.swagger.core.v3:swagger-annotations:$versionSwaggerAnnotations")

    // SpringDoc dependencies
    implementation("org.springdoc:springdoc-openapi-ui:$versionSpringdocOpenapiUi")

    // Jackson
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("org.openapitools:jackson-databind-nullable:$versionJacksonDatabindNullable")

    // Lombok
    compileOnly("org.projectlombok:lombok:$versionLombok")
    annotationProcessor("org.projectlombok:lombok:$versionLombok")
    testCompileOnly("org.projectlombok:lombok:$versionLombok")
    testAnnotationProcessor("org.projectlombok:lombok:$versionLombok")

    //JUnit
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Logback
    implementation("ch.qos.logback:logback-classic:$versionLogback")
    implementation("org.yaml:snakeyaml:$versionSnakeyaml")
}

openApiGenerate {
    generatorName.set("spring")
    library.set("spring-cloud")
    apiPackage.set("ru.astinezh.audience.event_manager.api")
    modelPackage.set("ru.astinezh.audience.event_manager.dto")
    inputSpec.set("$projectDir/openapi/event-manager.yaml")
    outputDir.set("$buildDir/openapi") // ПОСМОТРЕТЬ И ПОПРАВИТЬ!!!
    validateSpec.set(false)
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "interfaceOnly" to "true",
            "java8" to "true",
            "bigDecimalAsString" to "true",
            "delegatePattern" to "true",
            "useSpringBoot3" to "true" // использует зависимости из jakarta а не из javax
        )
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.compileKotlin {
    dependsOn(tasks.openApiGenerate)
}
