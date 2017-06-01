import org.gradle.api.plugins.ExtensionAware

import org.junit.platform.gradle.plugin.FiltersExtension
import org.junit.platform.gradle.plugin.EnginesExtension
import org.junit.platform.gradle.plugin.JUnitPlatformExtension

buildscript {
    repositories {
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-M4")
    }
}

allprojects {
    group = "com.qvidmi.kotlin-design-patterns"
    version = "1.0-SNAPSHOT"

    repositories {
        gradleScriptKotlin()
        jcenter()
    }

    apply {
        plugin("kotlin")
        plugin("org.junit.platform.gradle.plugin")
    }

    dependencies {
        val kotlin_version = "1.1.2-2"
        compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
        compile("org.junit.jupiter:junit-jupiter-api:5.0.0-M4")


        compile("ch.qos.logback:logback-classic:1.2.3")
        compile("io.github.microutils:kotlin-logging:1.4.4")
        testCompile("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
        testCompile("org.jetbrains.spek:spek-api:1.1.2") {
            exclude(group = "org.jetbrains.kotlin")
        }
        testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.2") {
            exclude(group = "org.junit.platform")
            exclude(group = "org.jetbrains.kotlin")
        }
        testCompile("org.amshove.kluent:kluent:1.20")
        testCompile("com.nhaarman:mockito-kotlin:1.4.0") {
            exclude(group = "org.mockito")
        }
        testCompile("org.mockito:mockito-core:2.8.9")

    }

    // extension for configuration
    fun JUnitPlatformExtension.filters(setup: FiltersExtension.() -> Unit) {
        when (this) {
            is ExtensionAware -> extensions.getByType(FiltersExtension::class.java).setup()
            else -> throw Exception("${this::class} must be an instance of ExtensionAware")
        }
    }

    fun FiltersExtension.engines(setup: EnginesExtension.() -> Unit) {
        when (this) {
            is ExtensionAware -> extensions.getByType(EnginesExtension::class.java).setup()
            else -> throw Exception("${this::class} must be an instance of ExtensionAware")
        }
    }
    configure<JUnitPlatformExtension> {
        filters {
            engines {
                include("spek")
            }
        }
    }
}

plugins {
    base
}

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}
