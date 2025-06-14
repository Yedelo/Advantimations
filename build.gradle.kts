val minecraftVersion: String by project
val yarnVersion: String by project
val fabricLoaderVersion: String by project
val fabricApiVersion: String by project

val modMenuVersion: String by project
val clothConfigVersion: String by project
val devAuthVersion: String by project

plugins {
	val loomVersion = "1.10-SNAPSHOT"
	id("fabric-loom") version loomVersion
}

repositories {
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://maven.shedaniel.me/")
	maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

dependencies {
	minecraft("com.mojang:minecraft:$minecraftVersion")
	mappings("net.fabricmc:yarn:$yarnVersion:v2")
	modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
	modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")

	modApi("com.terraformersmc:modmenu:$modMenuVersion")
	modApi("me.shedaniel.cloth:cloth-config-fabric:$clothConfigVersion")
	modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:$devAuthVersion")

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