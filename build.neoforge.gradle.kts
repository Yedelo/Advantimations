@file:OptIn(StonecutterExperimentalAPI::class)

import dev.kikugie.stonecutter.StonecutterExperimentalAPI
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.invoke

val yaclVersion: String
	get() {
		val rawVersionProperty = sc.properties.get<String>("versions.yacl")
		if (rawVersionProperty.endsWith("neoforge")) return rawVersionProperty
		return "$rawVersionProperty+${sc.current.version}-neoforge"
	}


interface NeoForgeMutex : BuildService<BuildServiceParameters.None>

val mutex = gradle.sharedServices.registerIfAbsent("createMinecraftArtifactsMutex", NeoForgeMutex::class.java) {
	maxParallelUsages.set(1)
}

plugins {
	id("net.neoforged.moddev") version "2.0.140"
	id("neoforge-mutex")
}

repositories {
	maven("https://maven.neoforged.net/releases/")
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

lateinit var maxMc: String
val rangedVersion = sc.properties.get<String>("versioning") == "range"
if (rangedVersion) {
	maxMc = sc.properties["mc.max"]
}

dependencies {
	implementation("dev.isxander:yet-another-config-lib:$yaclVersion")
}

neoForge {
	version = sc.properties["versions.neoforge"]

	interfaceInjectionData.from("../../neoforge.injections.json")

	mods {
		register("advantimations") {
			sourceSet(sourceSets.main.get())
		}
	}

	runs {
		register("client") {
			gameDirectory = file("../../run/")
			client()
		}
	}
}

tasks {
	processResources {
		fun MutableMap<String, String>.register(key: String, value: String) {
			inputs.property(key, value)
			set(key, value)
		}
		exclude("fabric.mod.json")
		exclude("advantimations.classtweaker")

		fun target(version: String) = "[$version,)"
		val props = buildMap {
			register("version", version.toString())
			register("yacl", target(yaclVersion))
			register("java", target(javaVersion.majorVersion))
			register("neoforge", target(sc.properties["versions.neoforge"]))
			val minecraftDependency =
				if (rangedVersion) "[${sc.current.version},${maxMc}]" else "[${sc.current.version}]"
			register("minecraft", minecraftDependency)

		}
		filesMatching(listOf("META-INF/neoforge.mods.toml")) { expand(props) }

		val mixinJava = "JAVA_${javaVersion.majorVersion}"
		filesMatching("advantimations.mixins.json5") { expand("mixinJava" to mixinJava) }

        outputs.upToDateWhen { false }
	}

	register<Copy>("buildAndCollect") {
		group = "build"

		from(jar.map { it.archiveFile })
		into(rootProject.layout.buildDirectory.file("libs"))
		dependsOn("build")
	}
	jar {
		val minecraftVersion = if (rangedVersion) "${sc.current.version}-$maxMc" else sc.current.version
		archiveFileName.set("Advantimations-$version+$minecraftVersion-neoforge.jar")
	}
}

// prevents neoforge from frying your computer by recompiling Minecraft on multiple versions
tasks.named { it == "createMinecraftArtifacts" }.configureEach {
	usesService(mutex)
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(javaVersion.majorVersion.toInt()))
	}
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
}