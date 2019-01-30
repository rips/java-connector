plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

dependencies {
    api(project(":api"))
    compileOnly(project(path = ":entity-gen", configuration = "generatedEntities"))
    implementation("commons-validator:commons-validator:1.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0")
    compile(kotlin("stdlib-jdk8"))
    testImplementation("org.mockito:mockito-core:2.23.4")
    testImplementation(project(path = ":entity-gen", configuration = "generatedEntities"))
}

publishing {
    publications {
        create<MavenPublication>("utils") {
            from(components["java"])
        }
    }
}