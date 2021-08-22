plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenCentral()
}

subprojects {
    apply {
        plugin("java")
        plugin("maven-publish")
        plugin("com.github.johnrengelman.shadow")
    }

    group = "net.itskev"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.mongojack:mongojack:4.2.1")

        compileOnly("org.projectlombok:lombok:1.18.20")
        annotationProcessor("org.projectlombok:lombok:1.18.20")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")
        testImplementation("org.mockito:mockito-junit-jupiter:3.12.0")
        testImplementation("de.bwaldvogel:mongo-java-server:1.38.0")

        testCompileOnly("org.projectlombok:lombok:1.18.20")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.20")
    }

    tasks.test {
        useJUnitPlatform()
    }

    val tokens = mapOf("VERSION" to project.version)

    tasks.withType<ProcessResources> {
        filesMatching("*.yml") {
            filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to tokens)
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        withJavadocJar()
    }

    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/ItsKev/persistence-framework")
                credentials {
                    username = System.getenv("USERNAME")
                    password = System.getenv("TOKEN")
                }
            }
        }
        publications {
            register<MavenPublication>("gpr") {
                from(components["java"])
            }
        }
    }
}
