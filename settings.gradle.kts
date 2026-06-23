pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net")
		maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.9.5"
	id("dev.kikugie.loom-back-compat") version "0.2"
}

loomx.loomVersion = "1.16-SNAPSHOT"

stonecutter {
	create(rootProject) {
		versions("1.21.4", "1.21.9", "1.21.11", "26.1", "26.2")
		vcsVersion = "26.1"
	}
}