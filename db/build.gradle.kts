import java.util.*

group = "kotlin-template"
version = rootProject.version

plugins {
    distribution
    id("org.liquibase.gradle")
}

dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core")
}

val lbProperties = Properties().apply {
    file("src/main/resources/liquibase.properties").inputStream().use { fis ->
        load(fis)
    }
}

liquibase {
    activities.register("data") {
        this.arguments = mapOf(
                "changeLogFile" to lbProperties.getProperty("changeLogFile"),
                "driver" to lbProperties.getProperty("driver"),
                "url" to lbProperties.getProperty("url"),
                "username" to lbProperties.getProperty("username"),
                "password" to lbProperties.getProperty("password")
        )
    }
}


//import org.springframework.boot.gradle.tasks.bundling.BootJar
//import java.util.*
//
//group = "ru.filit.guard"
//
//val env: String by project
//val buildVersionWithGitHash: String by project
//
//plugins {
//    id("org.springframework.boot")
//    id("org.liquibase.gradle")
//}
//
////springBoot {
////    buildInfo {
////        version = buildVersionWithGitHash
////    }
////}
//
//tasks.named<BootJar>("bootJar") {
//    version = "${rootProject.version}"
//}
//
//val liquibaseRuntime by configurations
//
//dependencies {
//    implementation("org.slf4j:slf4j-api")
//    implementation("org.slf4j:log4j-over-slf4j")
//    implementation("org.slf4j:jcl-over-slf4j")
//    implementation("ch.qos.logback:logback-core")
//    implementation("ch.qos.logback:logback-classic")
//
//    implementation("org.springframework.boot:spring-boot-starter-jdbc")
//
//    implementation("org.liquibase:liquibase-core")
//    implementation("org.postgresql:postgresql")
//
//    liquibaseRuntime("org.liquibase:liquibase-core")
//    liquibaseRuntime("org.postgresql:postgresql")
//    liquibaseRuntime("org.slf4j:slf4j-api")
//    liquibaseRuntime("org.slf4j:log4j-over-slf4j")
//    liquibaseRuntime("org.slf4j:jcl-over-slf4j")
//    liquibaseRuntime("ch.qos.logback:logback-core")
//    liquibaseRuntime("ch.qos.logback:logback-classic")
//}
//
//val dbRunList = if (project.hasProperty("dbRunList")) {
//    project.property("dbRunList") as String
//} else {
//    "objects, data_prod"
//}
//val fileName = "db-master-changelog.yml"
//
//var dbDataDriver = ""
//var dbDataUrl = ""
//var dbDataUser = ""
//var dbDataPassword = ""
//if (env in setOf("dev", "dev.docker")) {
//    val lbProperties = Properties().apply {
//        file("src/main/resources/wap-db-changesets/liquibase-local.properties").inputStream().use { fis ->
//            load(fis)
//        }
//    }
//    dbDataDriver = lbProperties.getProperty("driver")
//    dbDataUrl = lbProperties.getProperty("url")
//    dbDataUser = lbProperties.getProperty("username")
//    dbDataPassword = lbProperties.getProperty("password")
//} else {
//    dbDataDriver = "org.postgresql.Driver"
//    dbDataUrl = if (project.hasProperty("dbDataUrl")) {
//        project.property("dbDataUrl") as String
//    } else {
//        ""
//    }
//    dbDataUser = if (project.hasProperty("dbDataUser")) {
//        project.property("dbDataUser") as String
//    } else {
//        ""
//    }
//    dbDataPassword = if (project.hasProperty("dbDataPassword")) {
//        project.property("dbDataPassword") as String
//    } else {
//        ""
//    }
//}
//
//liquibase {
//    activities.register("objects") {
//        this.arguments = mapOf(
//                "changeLogFile" to "src/main/resources/wap-db-changesets/objects/$fileName",
//                "driver" to dbDataDriver,
//                "url" to dbDataUrl,
//                "username" to dbDataUser,
//                "password" to dbDataPassword
//        )
//    }
//    activities.register("data_prod") {
//        this.arguments = mapOf(
//                "changeLogFile" to "src/main/resources/wap-db-changesets/data/prod/$fileName",
//                "driver" to dbDataDriver,
//                "url" to dbDataUrl,
//                "username" to dbDataUser,
//                "password" to dbDataPassword
//        )
//    }
//    activities.register("data_dev") {
//        this.arguments = mapOf(
//                "changeLogFile" to "src/main/resources/wap-db-changesets/data/dev/$fileName",
//                "driver" to dbDataDriver,
//                "url" to dbDataUrl,
//                "username" to dbDataUser,
//                "password" to dbDataPassword
//        )
//    }
//    runList = dbRunList
//}
//
//tasks.register("dropDb") {
//    group = "database-postgresql"
//    doFirst {
//        println("dbDataUrl: $dbDataUrl")
//        println("fileName: $fileName")
//    }
//    finalizedBy(tasks.dropAll)
//}
//
//tasks.register("updateDb") {
//    group = "database-postgresql"
//    doFirst {
//        println("dbDataUrl: $dbDataUrl")
//        println("fileName: $fileName")
//    }
//    doLast {
//        liquibase.runList = "objects, data_prod"
//    }
//    finalizedBy(tasks.update)
//}
//
//tasks.register("updateDbDev") {
//    group = "database-postgresql"
//    doFirst {
//        println("dbDataUrl: $dbDataUrl")
//        println("fileName: $fileName")
//    }
//    doLast {
//        liquibase.runList = "objects, data_prod, data_dev"
//    }
//    finalizedBy(tasks.update)
//}
//
//tasks.register("dropDbAndUpdateDb") {
//    group = "database-postgresql"
//    dependsOn("dropDb")
//    dependsOn("updateDb")
//}
//
//tasks.register("dropDbAndUpdateDbDev") {
//    group = "database-postgresql"
//    dependsOn("dropDb")
//    dependsOn("updateDbDev")
//}