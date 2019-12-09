plugins {
    application
}

application {
    mainClassName = "com.ripstech.api.connector.Main"
}

dependencies {
	implementation(project(path = ":entity-gen", configuration = "generatedEntities"))
	implementation(project(":connector"))
}