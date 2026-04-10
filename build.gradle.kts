@file:OptIn(StonecutterExperimentalAPI::class)

import dev.kikugie.stonecutter.StonecutterExperimentalAPI

val minecraftVersion: String by project
val fabricLoaderVersion: String by project
val fabricApiVersion: String by project

val modMenuVersion: String by project
val yaclVersion: String by project

plugins {
	id("dev.kikugie.loom-back-compat")
}

repositories {
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://maven.isxander.dev/releases")
}

val javaVersion: JavaVersion = when {
	sc.current.parsed >= "26.1" -> JavaVersion.VERSION_25
	sc.current.parsed >= "1.20.5" -> JavaVersion.VERSION_21
	sc.current.parsed >= "1.18" -> JavaVersion.VERSION_17
	sc.current.parsed >= "1.17" -> JavaVersion.VERSION_16
	else -> JavaVersion.VERSION_1_8
}

lateinit var minMc: String
lateinit var maxMc: String
val rangedVersion = sc.properties.get<String>("versioning") == "range"
if (rangedVersion) {
	minMc = sc.properties["mc.min"]
	maxMc = sc.properties["mc.max"]
}

dependencies {
	minecraft("com.mojang:minecraft:${sc.current.version}")
	loomx.applyMojangMappings()
	modImplementation("net.fabricmc:fabric-loader:${property("versions.fabricLoader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("versions.fabricApi")}")

	modApi("com.terraformersmc:modmenu:${property("versions.modMenu")}")
	modImplementation("dev.isxander:yet-another-config-lib:${property("versions.yacl")}")
}

loom {
	accessWidenerPath = sc.process(
		rootProject.file("src/main/resources/advantimations.classtweaker"),
		"build/processed.classtweaker"
	)
	runConfigs.all {
		runDir = "../../run"
	}
}

tasks {
	processResources {
		fun MutableMap<String, String>.register(key: String, value: String) {
			inputs.property(key, value)
			set(key, value)
		}

		fun MutableMap<String, String>.registerDependencies(vararg names: String) {
			for (name in names) {
				register(name, sc.properties["targets.$name"])
			}
		}

		val props = buildMap {
			register("version", version.toString())
			registerDependencies("fabricLoader", "fabricApi", "yacl")
			register("java", ">=${javaVersion.majorVersion}")
			val minecraftDependency = if (rangedVersion) ">=${minMc} <=${maxMc}" else sc.current.version
			register("minecraft", minecraftDependency)
		}
		filesMatching("fabric.mod.json") { expand(props) }

		val mixinJava = "JAVA_${javaVersion.majorVersion}"
		filesMatching("advantimations.mixins.json5") { expand("mixinJava" to mixinJava) }

        outputs.upToDateWhen { false }
	}

	register<Copy>("buildAndCollect") {
		group = "build"

		// loomx.mod(Sources)Jar returns the jar task for the applied loom variant (but i said it louder)
		from(loomx.modJar.map { it.archiveFile }/*, loomx.modSourcesJar.map { it.archiveFile }*/)
		into(rootProject.layout.buildDirectory.file("libs"))
		dependsOn("build")
	}
	loomx.modJar {
		val minecraftVersion = if (rangedVersion) "$minMc-$maxMc" else sc.current.version
		archiveFileName.set("Advantimations-$version+$minecraftVersion.jar")
	}
}

java {
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
	// withSourcesJar()
}