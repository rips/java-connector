import net.ltgt.gradle.errorprone.errorprone
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    idea
    java
    `java-library`
    `maven-publish`
    id("org.sonarqube") version "2.7"
	id("net.ltgt.errorprone") version "0.7.1"
    id("org.ajoberstar.grgit") version "3.0.0"
    id("com.github.ben-manes.versions") version "0.20.0"
    kotlin("jvm") version "1.3.20" apply false
}

allprojects {
    group = "com.ripstech.api"
    version = "3.6.1"

    apply(plugin = "java")
    apply(plugin = "net.ltgt.errorprone")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    repositories {
        jcenter()
    }

    dependencies {
        implementation("org.jetbrains:annotations:16.0.3")
        errorprone("com.google.errorprone:error_prone_core:2.3.2")
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

        withType<JavaCompile> {
            with(options.errorprone) {
                //allDisabledChecksAsWarnings = true //is buggy right now
                disableWarningsInGeneratedCode = true
            }
        }

    }
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin(MavenPublishPlugin::class)) {
            extensions.configure<PublishingExtension> {
                publications.forEach {
                    if (it is MavenPublication) {
                        it.pom {

                            name.set("RIPS API Java Connector")
                            url.set("https://www.ripstech.com")

                            licenses {
                                license {
                                    name.set("BSD-3-Clause")
                                    url.set("https://github.com/rips/java-connector/blob/master/LICENSE")
                                    distribution.set("repo")
                                }
                            }

                            developers {
                                developer {
                                    name.set("Malena Ebert")
                                    email.set("mebert@ripstech.com")
                                    organization.set("RIPS Technologies GmbH")
                                    organizationUrl.set("https://ripstech.com")
                                }
                            }

                            scm {
                                connection.set("scm:git:git://github.com/rips/java-connector.git")
                                developerConnection.set("scm:git:ssh://github.com:rips/java-connector.git")
                                url.set("https://github.com/rips/java-connector/tree/master")
                            }

                        }
                    }
                }
            }
        }
    }

}
