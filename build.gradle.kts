import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "de.floribe2000"
version = "1.0.0-RC5"
description = "A java/kotlin library to make it easier to interact with the Wargaming API."

plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
    id("org.jetbrains.dokka") version "1.4.20"
    idea
}

repositories {
    mavenCentral()
    maven("https://maven.floribe2000.de/")
}

dependencies {
    api("com.google.code.gson:gson:2.8.6")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    api("org.slf4j:slf4j-api:1.7.30")
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    testRuntimeOnly("ch.qos.logback:logback-classic:1.2.3")
    testImplementation(platform("org.junit:junit-bom:5.7.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    wrapper {
        gradleVersion = "6.8.3"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }

    withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
        dokkaSourceSets {
            configureEach {
                includes.from("Module.md")
                reportUndocumented.set(true)
            }
        }
    }

    test {
        val testApiKey: String by project
        environment("APIKEY", testApiKey)
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
        reports {
            junitXml.isEnabled = true
        }
    }

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
