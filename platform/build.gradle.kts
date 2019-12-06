plugins {
    `java-platform`
    `maven-publish`
    signing
}

dependencies {
    constraints {
        api(project(":connector"))
        api(project(":utils"))
    }
}

publishing {
    publications {
        create<MavenPublication>("connectorPlatform") {
            artifactId = "connector-platform"
            from(components["javaPlatform"])
            pom {
                description.set("The platform distribution of the connector and utils for the RIPS API.")
            }
        }
    }
}