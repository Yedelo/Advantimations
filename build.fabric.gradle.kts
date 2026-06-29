@file:OptIn(StonecutterExperimentalAPI::class)

import dev.kikugie.stonecutter.StonecutterExperimentalAPI
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.invoke

val yaclVersion = "${sc.properties.get<String>("versions.yacl")}+${sc.current.version}-fabric"

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

lateinit var maxMc: String
val rangedVersion = sc.properties.get<String>("versioning") == "range"
if (rangedVersion) {
	maxMc = sc.properties["mc.max"]
}

dependencies {
	minecraft("com.mojang:minecraft:${sc.current.version}")
	loomx.applyMojangMappings()
	modImplementation("net.fabricmc:fabric-loader:${property("versions.fabricLoader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("versions.fabricApi")}")

	modApi("com.terraformersmc:modmenu:${property("versions.modMenu")}")
	modImplementation("dev.isxander:yet-another-config-lib:$yaclVersion")
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
		exclude("META-INF/neoforge.mods.toml")

		fun target(version: String) = ">=$version"
		val props = buildMap {
			register("version", version.toString())
			register("yacl", target(yaclVersion))
			register("java", target(javaVersion.majorVersion))
			register("fabricLoader", target(sc.properties["versions.fabricLoader"]))
			val minecraftDependency =
				if (rangedVersion) ">=${sc.current.version} <=${maxMc}" else sc.current.version
			register("minecraft", minecraftDependency)
		}
		filesMatching(listOf("fabric.mod.json")) { expand(props) }

		val mixinJava = "JAVA_${javaVersion.majorVersion}"
		filesMatching("advantimations.mixins.json5") { expand("mixinJava" to mixinJava) }

        outputs.upToDateWhen { false }
	}

	register<Copy>("buildAndCollect") {
		group = "build"

		from(loomx.modJar.map { it.archiveFile })
		into(rootProject.layout.buildDirectory.file("libs"))
		dependsOn("build")
	}
	loomx.modJar {
		val minecraftVersion = if (rangedVersion) "${sc.current.version}-$maxMc" else sc.current.version
		archiveFileName.set("Advantimations-$version+$minecraftVersion-fabric.jar")
	}
}

java {
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
}