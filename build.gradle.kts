import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "de.floribe2000"
version = "1.0.0-alpha"
description = "A java/kotlin library to make it easier to interact with the Wargaming API."

plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.dokka") version "1.4.10"
    idea
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo1.maven.org/maven2")
    }
    maven {
        url = uri("https://maven.floribe2000.de/")
    }
    gradlePluginPortal()
    jcenter()
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.apache.commons:commons-collections4:4.4")
    testImplementation("junit:junit:4.13.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    dokkaSourceSets {
        configureEach {
            includes.from("Module.md")
            reportUndocumented.set(true)
        }
    }
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    val javadocJar by creating(Jar::class) {
        dependsOn.add(dokkaJavadoc)
        archiveClassifier.set("javadoc")
        from(dokkaJavadoc)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
        archives(jar)
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

        }
    }
}
