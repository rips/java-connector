import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    idea
    java
    `java-library`
    `maven-publish`
    id("org.sonarqube") version "2.7"
    id("org.ajoberstar.grgit") version "3.0.0"
    id("com.github.ben-manes.versions") version "0.20.0"
    kotlin("jvm") version "1.3.11" apply false
}

fun RepositoryHandler.rips(action: MavenArtifactRepository.() -> Unit = {}) {
    maven {
        url = uri("https://nexus.internal.ripstech.com/repository/maven-rips-" +
                  if(project.hasProperty("release"))
                      "release"
                  else
                      "snapshot"
        )
        name = "rips"
        action.invoke(this)
    }
}

fun MavenArtifactRepository.credentialsFromProps() {
    if (project.hasProperty("nexusUser")) {
        credentials {
            username = project.properties["nexusUser"] as String
            password = project.properties["nexusPassword"] as String
        }
    }
}

allprojects {
    group = "com.ripstech.api"
    version = "3.0.0" + if(project.hasProperty("release")) "" else "-SNAPSHOT"
}

subprojects {

    apply(plugin = "java")
    apply(plugin = "maven-publish")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    repositories {
        jcenter()
    }

    dependencies {
        implementation("org.jetbrains:annotations:16.0.3")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
        testCompile("org.junit.jupiter:junit-jupiter-params:5.3.2")
        testRuntime("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    }

    tasks {

        withType<Test> {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }

    }

    publishing {
        repositories {
            rips {
                credentialsFromProps()
            }
        }
    }

}
