

plugins {
    kotlin("jvm")
    application
}

sourceSets {
    create("generated")
}

val generatedImplementation by configurations.getting {}
val generatedEntities = configurations.create("generatedEntities").name

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.squareup", "javapoet", "1.11.1")
    implementation("io.swagger.parser.v3", "swagger-parser", "2.0.8")
    implementation("org.apache.commons", "commons-text", "1.6")
    implementation("org.apache.logging.log4j", "log4j-api-kotlin", "1.0.0")
    implementation("org.apache.logging.log4j", "log4j-api", "2.11.1")
    implementation("org.apache.logging.log4j", "log4j-core", "2.11.1")
    implementation("org.apache.logging.log4j", "log4j-slf4j-impl", "2.11.1")
    generatedImplementation("com.fasterxml.jackson.core", "jackson-annotations", "2.9.8")
    generatedImplementation("org.jetbrains:annotations:16.0.3")
}

application {
    mainClassName = "com.ripstech.api.entity.MainKt"
}

val generatedJar by tasks.registering(Jar::class) {
    archiveBaseName.set("entities")
    from(project.the<SourceSetContainer>()["generated"].output)
}

tasks.withType<Delete> {
    delete.add(project.the<SourceSetContainer>()["generated"].allSource.sourceDirectories)
}

artifacts {
    add("generatedEntities", generatedJar)
}
