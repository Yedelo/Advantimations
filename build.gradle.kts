val loomVersion: String by project
val minecraftVersion: String by project
val yarnVersion: String by project
val fabricLoaderVersion: String by project
val fabricApiVersion: String by project

plugins {
	val loomVersion = "1.10-SNAPSHOT"
	id("fabric-loom") version loomVersion
}

dependencies {
	minecraft("com.mojang:minecraft:$minecraftVersion")
	mappings("net.fabricmc:yarn:$yarnVersion:v2")
	modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
	modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
}

tasks {
	processResources {
		filesMatching("fabric.mod.json") {
			expand(mapOf("version" to version))
		}
	}
}

java {
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}