import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "kotlin-template"
version = rootProject.version

val artifactBaseName = "demo-back"
val artifactVersion = "$version"

plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
    distribution
    `maven-publish`
}

springBoot {
    buildInfo {
        properties {
            name = artifactBaseName
        }
    }
}

tasks.named<BootJar>("bootJar") {
    archiveBaseName.set(artifactBaseName)
    archiveVersion.set(artifactVersion)
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j")
    implementation("io.github.microutils:kotlin-logging")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework.retry:spring-retry")

    implementation("org.springframework.data:spring-data-r2dbc")
    implementation("io.r2dbc:r2dbc-pool")
    implementation("org.apache.commons:commons-pool2")

    implementation("io.projectreactor:reactor-core")
    implementation("com.zaxxer:HikariCP")
    implementation("commons-io:commons-io")
    implementation("commons-lang:commons-lang")

    testImplementation("ch.qos.logback:logback-classic")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core")
    testImplementation("com.google.jimfs:jimfs")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("com.github.tomakehurst:wiremock")
}
