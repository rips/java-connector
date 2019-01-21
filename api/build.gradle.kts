plugins {
    `maven-publish`
    `java-library`
}

val includeInJar: Configuration by configurations.creating
configurations {
    compileClasspath.get().extendsFrom(includeInJar)
    testImplementation.get().extendsFrom(includeInJar)
}

dependencies {
    includeInJar(project(path = ":entity-gen", configuration = "generatedEntities"))
    implementation("com.fasterxml.jackson.core:jackson-core:2.9.8")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.8") {
        because("java 8 date use in entities")
    }
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.8") {
        because("java optional use in entities")
    }
    implementation("com.squareup.okhttp3:okhttp:3.12.1")

    testCompile("org.reflections:reflections:0.9.11")
}

tasks {
    jar {
        from(zipTree(includeInJar.asPath))
    }
}
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("connector") {
            artifactId = "connector"
            from(components["java"])
            //artifact(sourcesJar.get())
        }
    }
}