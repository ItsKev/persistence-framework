repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation(project(":persistence-framework-common"))
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}
