// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.gradlePlugin apply false
    id("com.android.library") version Versions.gradlePlugin apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlint
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
