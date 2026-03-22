val minecraftVersion: String by project
val fabricLoaderVersion: String by project
val fabricApiVersion: String by project

val modMenuVersion: String by project
val yaclVersion: String by project
val devAuthVersion: String by project

plugins {
	val loomVersion = "1.15-SNAPSHOT"
	id("net.fabricmc.fabric-loom") version loomVersion
}

repositories {
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://maven.isxander.dev/releases")
}

dependencies {
	minecraft("com.mojang:minecraft:$minecraftVersion")
	implementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
	implementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")

	api("com.terraformersmc:modmenu:$modMenuVersion")
	implementation("dev.isxander:yet-another-config-lib:${yaclVersion}")
}

loom {
    accessWidenerPath = file("src/main/resources/advantimations.classtweaker")
}

tasks {
    jar {
        archiveFileName.set("Advantimations-$version-1.21.11.jar")
    }
	processResources {
		filesMatching("fabric.mod.json") {
			expand(mapOf(
				"version" to version,
				"yaclVersion" to yaclVersion
			))
		}
        outputs.upToDateWhen { false }
	}
}

java {
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}