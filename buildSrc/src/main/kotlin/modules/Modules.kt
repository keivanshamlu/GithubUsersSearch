package modules

object Modules {

    const val APP = ":app"
    const val DOMAIN = ":domain"
    const val CORE = ":core"
    const val DATA = ":data"
    const val NAVIGATION = ":navigation"
    const val DESIGN_SYSTEM = ":designSystem"
    const val BASES = ":bases"
    object Feature {
        private const val dir = ":features"
        const val SEARCH = "$dir:search"
    }
}