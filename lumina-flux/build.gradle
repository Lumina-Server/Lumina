plugins {
    `java-library`
}

dependencies {

    // Core Lumina
    implementation(project(":lumina-api"))
    implementation(project(":lumina-network"))
    implementation(project(":lumina-server"))

    // Prism compatibility
    implementation(project(":lumina-prism"))

    // Netty UDP/RakNet
    implementation("io.netty:netty-all:4.1.108.Final")

    // JSON
    implementation("com.google.code.gson:gson:2.10.1")

    // NBT
    implementation("org.cloudburstmc:nbt:3.0.0")

    // Compression
    implementation("org.lz4:lz4-java:1.8.0")

    // Logging
    implementation("org.slf4j:slf4j-api:2.0.13")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }

    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(21)
}
