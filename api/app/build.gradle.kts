plugins {
    application
}

application {
    mainClassName = "com.ripstech.apiconnector2.Main"
}

dependencies {
    implementation(project(":api"))
}