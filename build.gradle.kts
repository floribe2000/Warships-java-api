import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "de.floribe2000"
version = "1.0.0-beta8"
description = "A java/kotlin library to make it easier to interact with the Wargaming API."

plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.21"
    id("org.jetbrains.dokka") version "1.4.20"
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
    jcenter()
}

dependencies {
    api("com.google.code.gson:gson:2.8.6")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    api("org.slf4j:slf4j-api:1.7.30")
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.apache.commons:commons-collections4:4.4")
    testRuntimeOnly("ch.qos.logback:logback-classic:1.2.3")
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    wrapper {
        gradleVersion = "6.7.1"
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
