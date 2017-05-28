allprojects {

    group = "org.qvidmi.kotlin-design-patterns"

    version = "1.0-SNAPSHOT"

    repositories {
        gradleScriptKotlin()
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
