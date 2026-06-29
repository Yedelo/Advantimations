plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "26.2-fabric"

stonecutter parameters {
    constants["spear"] = current.parsed >= "1.21.11"

    constants {
        match(current.project.split("-")[1], "fabric", "neoforge")
    }

    replacements {
        string(current.parsed >= "1.21.11") {
            replace("ResourceLocation", "Identifier")
        }

        string(current.parsed < "26.1") {
            replace("classTweaker v1 official", "classTweaker v1 named")
        }
    }
}