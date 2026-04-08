@file:OptIn(StonecutterExperimentalAPI::class)

import dev.kikugie.stonecutter.StonecutterExperimentalAPI

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

val javaVersion: JavaVersion = when {
	sc.current.parsed >= "26.1" -> JavaVersion.VERSION_25
	sc.current.parsed >= "1.20.5" -> JavaVersion.VERSION_21
	sc.current.parsed >= "1.18" -> JavaVersion.VERSION_17
	sc.current.parsed >= "1.17" -> JavaVersion.VERSION_16
	else -> JavaVersion.VERSION_1_8
}

val minMc: String = sc.properties["mc.min"]
val maxMc: String = sc.properties["mc.max"]

repositories {
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://maven.isxander.dev/releases")
}

dependencies {
	minecraft("com.mojang:minecraft:${sc.current.version}")
	implementation("net.fabricmc:fabric-loader:${property("versions.fabricLoader")}")
	implementation("net.fabricmc.fabric-api:fabric-api:${property("versions.fabricApi")}")

	api("com.terraformersmc:modmenu:${property("versions.modMenu")}")
	implementation("dev.isxander:yet-another-config-lib:${property("versions.yacl")}")
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
    jar {
        archiveFileName.set("Advantimations-$version-${minMc}-${maxMc}.jar")
    }
	processResources {
		fun MutableMap<String, String>.register(key: String, property: String) {
			val value: String = sc.properties[property]
			inputs.property(key, value)
			set(key, value)
		}

		fun MutableMap<String, String>.registerDependencies(vararg names: String) {
			for (name in names) {
				register(name, "targets.$name")
			}
		}

		val props = buildMap {
			register("version", "version")
			registerDependencies("fabricLoader", "fabricApi", "yacl")
			set("java", ">=${javaVersion.majorVersion}")
			set("minecraft", ">=${minMc} <=${maxMc}")
		}
		filesMatching("fabric.mod.json") { expand(props) }

		val mixinJava = "JAVA_${javaVersion.majorVersion}"
		filesMatching("*.mixins.json") { expand("java" to mixinJava) }

        outputs.upToDateWhen { false }
	}

	register<Copy>("buildAndCollect") {
		group = "build"

		from(jar.map { it.archiveFile })
		into(rootProject.layout.buildDirectory.file("libs"))
		dependsOn("build")
	}
}

java {
	withSourcesJar()

	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
}