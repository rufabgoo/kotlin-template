import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

group = "kotlin-template"
version = "0.0.1-SNAPSHOT"


buildscript {

	apply(from = "versions.gradle.kts")

	repositories {
		mavenCentral()
		jcenter()
		maven {
			url = uri("https://plugins.gradle.org/m2/")
		}
	}

	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("kotlinVersion")}")
		classpath("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:${property("kotlinVersion")}")
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${property("springBootVersion")}")
		classpath("io.spring.gradle:dependency-management-plugin:${property("springDependencyManagementPluginVersion")}")
		classpath("org.ajoberstar:gradle-git:${property("gitPluginVersion")}")
		classpath("org.liquibase:liquibase-gradle-plugin:${property("liquibasePluginVersion")}")
		classpath("com.google.protobuf:protobuf-gradle-plugin:${property("protobufPluginVersion")}")
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "maven-publish")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin")

	repositories {
		mavenCentral()
		jcenter()
		maven {
			url = uri("https://plugins.gradle.org/m2/")
		}
	}

	val compileOnly by configurations
	val annotationProcessor by configurations
	compileOnly.extendsFrom(annotationProcessor)

	configure<JavaPluginConvention> {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	configure<DependencyManagementExtension> {
		dependencies {
			dependency("org.slf4j:slf4j-api:${property("slf4jVersion")}")
			dependency("org.slf4j:slf4j-simple:${property("slf4jVersion")}")
			dependency("org.slf4j:log4j-over-slf4j:${property("slf4jVersion")}")
			dependency("org.slf4j:jcl-over-slf4j:${property("slf4jVersion")}")
			dependency("ch.qos.logback:logback-core:${property("logbackVersion")}")
			dependency("ch.qos.logback:logback-classic:${property("logbackVersion")}")

			dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${property("kotlinVersion")}")
			dependency("org.jetbrains.kotlin:kotlin-reflect:${property("kotlinVersion")}")
			dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:${property("kotlinCoroutinesVersion")}")
			dependency("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${property("kotlinCoroutinesVersion")}")
			dependency("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:${property("kotlinCoroutinesVersion")}")
			dependency("io.github.microutils:kotlin-logging:${property("kotlinLoggingVersion")}")

			dependency("org.springframework:spring-core:${property("springVersion")}")
			dependency("org.springframework:spring-context:${property("springVersion")}")
			dependency("org.springframework:spring-context-support:${property("springVersion")}")
			dependency("org.springframework:spring-web:${property("springVersion")}")
			dependency("org.springframework:spring-webmvc:${property("springVersion")}")
			dependency("org.springframework:spring-webflux:${property("springVersion")}")
			dependency("org.springframework:spring-jdbc:${property("springVersion")}")
			dependency("org.springframework:spring-aop:${property("springVersion")}")
			dependency("org.springframework:spring-expression:${property("springVersion")}")
			dependency("org.springframework:spring-jcl:${property("springVersion")}")
			dependency("org.springframework.data:spring-data-commons:${property("springDataVersion")}")
			dependency("org.springframework.data:spring-data-jpa:${property("springDataVersion")}")
//			dependency("org.springframework.data:spring-data-r2dbc:${property("springDataR2dbcVersion")}")

			dependency("org.springframework.boot:spring-boot:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter-data-jpa:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter-data-redis-reactive:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter-web:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter-webflux:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter-jdbc:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter-aop:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter-actuator:${property("springBootVersion")}")
			dependency("org.springframework.boot:spring-boot-starter-reactor-netty:${property("springBootVersion")}")

			dependency("org.springframework.retry:spring-retry:${property("springRetryVersion")}")

			dependency("com.zaxxer:HikariCP:${property("hikariCPVersion")}")
			dependency("org.hibernate:hibernate-core:${property("hibernateVersion")}")
			dependency("org.hibernate:hibernate-entitymanager:${property("hibernateVersion")}")
			dependency("org.hibernate:hibernate-validator:${property("hibernateValidatorVersion")}")
			dependency("javax.persistence:javax.persistence-api:${property("javaxPersistenceVersion")}")
			dependency("javax.validation:validation-api:${property("javaxValidationVersion")}")

			dependency("org.liquibase:liquibase-core:${property("liquibaseVersion")}")
			dependency("io.r2dbc:r2dbc-pool:${property("r2dbcPoolVersion")}")
			dependency("io.lettuce:lettuce-core:${property("lettuceVersion")}")

			dependency("javax.activation:javax.activation-api:${property("javaxActivationVersion")}")

			dependency("io.projectreactor:reactor-core:${property("reactorVersion")}")
			dependency("io.projectreactor.kotlin:reactor-kotlin-extensions:${property("reactorKotlinVersion")}")

			dependency("org.apache.commons:commons-lang3:${property("commonsLang3Version")}")
			dependency("org.apache.commons:commons-csv:${property("commonsCsvVersion")}")
			dependency("org.apache.commons:commons-math3:${property("commonsMath3Version")}")
			dependency("org.apache.commons:commons-pool2:${property("commonsPool2Version")}")
			dependency("commons-lang:commons-lang:${property("commonsLang2Version")}")
			dependency("commons-codec:commons-codec:${property("commonsCodecVersion")}")
			dependency("commons-io:commons-io:${property("commonsIoVersion")}")
			dependency("commons-net:commons-net:${property("commonsNetVersion")}")

			dependency("com.google.protobuf:protobuf-java:${property("protobufVersion")}")
			dependency("com.google.protobuf:protoc:${property("protobufVersion")}")
			dependency("com.google.protobuf:protobuf-java-util:${property("protobufVersion")}")
			dependency("com.google.guava:guava:${property("guavaVersion")}")
			dependency("com.google.guava:guava-collections:${property("guavaCollectionsVersion")}")

			dependency("org.reflections:reflections:${property("reflectionsVersion")}")
			dependency("io.vavr:vavr:${property("vavrVersion")}")
			dependency("net.sf.trove4j:trove4j:${property("trove4jVersion")}")
			dependency("com.github.ben-manes.caffeine:caffeine:${property("caffeineVersion")}")
			dependency("io.github.resilience4j:resilience4j-retry:${property("resilience4jVersion")}")

			dependency("org.springframework.boot:spring-boot-starter-test:${property("springBootVersion")}") {
				exclude("org.junit.vintage:junit-vintage-engine")
				exclude("com.vaadin.external.google:android-json")
			}

			dependency("org.junit.jupiter:junit-jupiter:${property("junitVersion")}")
			dependency("org.mockito:mockito-core:${property("mockitoVersion")}")
			dependency("org.mockito:mockito-junit-jupiter:${property("mockitoVersion")}")
			dependency("org.assertj:assertj-core:${property("assertjVersion")}")
			dependency("com.google.jimfs:jimfs:${property("jimfsVersion")}")
			dependency("com.github.tomakehurst:wiremock:${property("wiremockVersion")}")
			dependency("io.projectreactor:reactor-test:${property("reactorVersion")}")
			dependency("org.jetbrains.kotlinx:kotlinx-coroutines-test:${property("kotlinTestVersion")}")
			dependency("com.nhaarman.mockitokotlin2:mockito-kotlin:${property("mockitoKotlinVersion")}")

			dependency("org.projectlombok:lombok:${property("lombokVersion")}")
			dependency("com.github.docker-java:docker-java:${property("dockerClientVersion")}")

			imports {
				mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
			}

			dependency("io.qameta.allure:allure-java-commons:${property("allureVersion")}")
			dependency("io.qameta.allure:allure-junit-platform:${property("allureVersion")}")
			dependency("io.qameta.allure:allure-junit5:${property("allureVersion")}")
		}
	}
}
