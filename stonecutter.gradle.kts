import kotlin.reflect.KProperty

plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "26.2-fabric"

stonecutter parameters {
    val loader = current.project.split("-")[1]

    constants["spear"] = current.parsed >= "1.21.11"

    constants {
        match(loader, "fabric", "neoforge")
    }

    replacements {
        string(current.parsed >= "1.21.11") {
            replace("ResourceLocation", "Identifier")
        }

        string(current.parsed < "26.1") {
            replace("classTweaker v1 official", "classTweaker v1 named")
        }
    }

    val shared = mutableMapOf<String, Any?>()
    extra[current.project] = shared

    class Declare<T>(private val value: T) {
        operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): Declare<T> {
            shared[property.name] = value
            return this
        }

        operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value
    }

    val yaclVersion by Declare(run {
        val rawVersionProperty = properties.get<String>("versions.yacl")
        if (rawVersionProperty.endsWith(loader)) rawVersionProperty else "$rawVersionProperty+${current.project}"
    })

    val javaVersion by Declare(run {
        val mc = current.parsed
        when {
            mc >= "26.1" -> JavaVersion.VERSION_25
            mc >= "1.20.5" -> JavaVersion.VERSION_21
            mc >= "1.18" -> JavaVersion.VERSION_17
            mc >= "1.17" -> JavaVersion.VERSION_16
            else -> JavaVersion.VERSION_1_8
        }
    })

    val rangedVersion by Declare(properties.get<String>("versioning") == "range")
    val maxMc by Declare(if (rangedVersion) properties.get<String>("mc.max") else null)

    val minecraftTarget = if (rangedVersion) "${current.version}-$maxMc" else current.version
    val finalFileName by Declare("Advantimations-$version+$minecraftTarget-$loader.jar")
}
