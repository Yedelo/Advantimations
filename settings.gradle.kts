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
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

loomx.loomVersion = "1.16-SNAPSHOT"

stonecutter {
	create(rootProject) {
		for (versionString in listOf("1.21.4", "1.21.9", "1.21.11", "26.1", "26.2")) {
			version("$versionString-fabric", versionString).buildscript("build.fabric.gradle.kts")
			version("$versionString-neoforge", versionString).buildscript("build.neoforge.gradle.kts")
		}
		vcsVersion = "26.2-fabric"
	}
}