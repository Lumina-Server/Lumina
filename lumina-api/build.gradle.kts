// lumina-api/build.gradle.kts
plugin {
   'java-library'
}
dependencies {
    api(project(":lumina-common"))
    api(project(":lumina-event"))
    api(project(":lumina-scheduler"))
    api(project(":lumina-command"))
    api(project(":lumina-world"))
    api(project(":lumina-entity"))
    api(project(":lumina-logging"))
}
