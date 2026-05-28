plugins {
    id("com.gradleup.shadow") version "9.0.0"
    java
}

allprojects {
    group = "dev.lumina"
    version = "1.0.0"

    repositories {
        mavenCentral()

        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.opencollab.dev/main/")
    }
}

subprojects {

    apply(plugin = "java-library")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
}
tasks.shadowJar {
    archiveClassifier.set("")

    destinationDirectory.set(
        file("$rootDir/Lumina")
    )

    archiveFileName.set("lumina-server.jar")
}
