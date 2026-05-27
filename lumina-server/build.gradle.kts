plugins {
    application
    java
}

dependencies {

    implementation(project(":lumina-api"))
    implementation(project(":lumina-network"))
    implementation(project(":lumina-prism"))
    implementation(project(":lumina-flux"))

    implementation("io.netty:netty-all:4.1.108.Final")
    implementation("com.google.code.gson:gson:2.10.1")
}

application {
    mainClass.set("dev.lumina.server.LuminaServer")
}

tasks.jar {

    manifest {
        attributes(
            "Main-Class" to "dev.lumina.server.LuminaServer"
        )
    }
}
