import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.Configuration
import org.jooq.meta.jaxb.Database
import org.jooq.meta.jaxb.Generator
import org.jooq.meta.jaxb.Jdbc
import org.jooq.meta.jaxb.Target
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.MountableFile

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.postgresql:postgresql:42.5.0")
        classpath("org.testcontainers:postgresql:1.17.6")
        classpath("org.jooq:jooq-codegen:3.18.7")
    }
}

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
}

repositories {
    mavenCentral()
}

group = "me.vladkanash"
version = "0.0.1-SNAPSHOT"

dependencyManagement {
    dependencies {
        dependency("org.springframework:spring-boot-dependencies:3.2.0")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jooq:jooq-kotlin:3.18.7")
    implementation("org.jooq:jooq-kotlin-coroutines:3.18.7")

    implementation("org.postgresql:r2dbc-postgresql:1.0.1.RELEASE")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:r2dbc")
}

tasks.withType<KotlinCompile> {
    dependsOn("jooqCodegen")
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.register("jooqCodegen") {
    doLast {
        val schemaPath = layout.projectDirectory.file("/src/main/resources/schema.sql").asFile.path
        val containerInstance = PostgreSQLContainer<Nothing>("postgres:16.1")
            .apply {
                withCopyToContainer(MountableFile.forHostPath(schemaPath), "/docker-entrypoint-initdb.d/init.sql")
                start()
            }

        Configuration()
            .withJdbc(
                Jdbc()
                    .withDriver("org.postgresql.Driver")
                    .withUrl(containerInstance.jdbcUrl)
                    .withUser(containerInstance.username)
                    .withPassword(containerInstance.password)
            )
            .withGenerator(
                Generator()
                    .withDatabase(Database().withInputSchema("public"))
                    .withTarget(
                        Target()
                            .withPackageName("org.jooq.generated")
                            .withDirectory("${layout.projectDirectory}/src/main/java")
                    )
            ).also(GenerationTool::generate)

        containerInstance.stop()
    }
}
