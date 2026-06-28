@file:OptIn(StonecutterExperimentalAPI::class)

import dev.kikugie.stonecutter.StonecutterExperimentalAPI

val loader = sc.current.project.split("-")[1]
val fabric = loader == "fabric"
val neoforge = loader == "neoforge"

val yaclVersion = "${sc.properties.get<String>("versions.yacl")}+${sc.current.project}"

plugins {
	id("dev.kikugie.loom-back-compat")
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
	minecraft("com.mojang:minecraft:${sc.current.version}")
	loomx.applyMojangMappings()
	if (fabric) {
		modImplementation("net.fabricmc:fabric-loader:${property("versions.fabricLoader")}")
		modImplementation("net.fabricmc.fabric-api:fabric-api:${property("versions.fabricApi")}")

		modApi("com.terraformersmc:modmenu:${property("versions.modMenu")}")
	}
	else if (neoforge) {
		implementation("net.neoforged:neoforge:${property("versions.neoforge")}")
		implementation("net.neoforged.fancymodloader:loader:${property("versions.fancyModLoader")}")
		// highly unfortunate because nfml already includes fabric mixin, but it's whatever
		compileOnly("net.fabricmc:sponge-mixin:${property("versions.neoforgeMixin")}")
		// highly unfortunate because neoforge already includes mixinextras, but it's whatever
		compileOnly("io.github.llamalad7:mixinextras-fabric:${property("versions.mixinExtras")}")
	}
	modImplementation("dev.isxander:yet-another-config-lib:$yaclVersion")
}

stonecutter {
	constants.match(loader, "fabric", "neoforge")
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

		fun target(version: String) = if (fabric) ">=$version" else "[$version,)"

			val props = buildMap {
				register("version", version.toString())
				register("yacl", target(yaclVersion))
				register("java", target(javaVersion.majorVersion))
				// for certain versions, don't cause problems with missing template properties
				if (fabric) {
					exclude("neoforge.mods.toml")
					register("fabricLoader", target(sc.properties["versions.fabricLoader"]))
					val minecraftDependency = if (rangedVersion) ">=${sc.current.version} <=${maxMc}" else sc.current.version
					register("minecraft", minecraftDependency)
				}
				else if (neoforge) {
					exclude("fabric.mod.json")
					register("neoforge", target(sc.properties["versions.neoforge"]))
					val minecraftDependency = if (rangedVersion) "[${sc.current.version},${maxMc}]" else "[${sc.current.version}]"
					register("minecraft", minecraftDependency)
				}
			}
			filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml")) { expand(props) }

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
		val minecraftVersion = if (rangedVersion) "${sc.current.version}-$maxMc" else sc.current.version
		archiveFileName.set("Advantimations-$version+$minecraftVersion+$loader.jar")
	}
}

java {
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
	// withSourcesJar()
}