plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "26.1"

stonecutter parameters {
    constants["spear"] = current.parsed >= "1.21.11"

    replacements {
        string(current.parsed >= "1.21.11") {
            replace("ResourceLocation", "Identifier")
        }

        string(current.parsed < "26.1") {
            replace("classTweaker v1 official", "classTweaker v1 named")
        }
    }
}