val minecraftVersion: String by project
val fabricLoaderVersion: String by project
val fabricApiVersion: String by project

val modMenuVersion: String by project
val yaclVersion: String by project
val devAuthVersion: String by project

plugins {
	val loomVersion = "1.15-SNAPSHOT"
	id("fabric-loom") version loomVersion
}

repositories {
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://maven.isxander.dev/releases")
	maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

dependencies {
	minecraft("com.mojang:minecraft:$minecraftVersion")
	mappings(loom.officialMojangMappings())
	modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
	modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")

	modApi("com.terraformersmc:modmenu:$modMenuVersion")
	modImplementation("dev.isxander:yet-another-config-lib:${yaclVersion}")
	modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:$devAuthVersion")
}

loom {
    accessWidenerPath = file("src/main/resources/advantimations.classtweaker")
}

tasks {
    remapJar {
        archiveFileName.set("Advantimations-$version-1.21.9-1.21.10.jar")
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

	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}