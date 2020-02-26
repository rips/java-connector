include("platform", "connector", "connector:app", "entity-gen", "utils")

pluginManagement {
    repositories {
        maven(uri("https://nexus.internal.ripstech.com/repository/maven-public-and-rips/"))
        gradlePluginPortal()
    }
}
