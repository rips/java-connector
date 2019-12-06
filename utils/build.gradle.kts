plugins {
    `java-library`
    `maven-publish`
    signing
    kotlin("jvm")
}

dependencies {
    api(project(":connector"))
    compileOnly(project(path = ":entity-gen", configuration = "generatedEntities"))
    implementation("commons-validator:commons-validator:1.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0")
    compile(kotlin("stdlib-jdk8"))
    testImplementation("org.mockito:mockito-core:2.23.4")
    testImplementation(project(path = ":entity-gen", configuration = "generatedEntities"))
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc)
}

publishing {
    publications {
        create<MavenPublication>("utils") {
            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())
            pom {
                description.set("A library which provides utils for scan management.")
            }
        }
    }
}