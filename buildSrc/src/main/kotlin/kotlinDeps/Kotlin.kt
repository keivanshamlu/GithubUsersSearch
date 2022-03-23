package kotlinDeps

object Kotlin {
    const val version = "1.5.20"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"

    object Coroutine {
        private const val version = "1.5.0"// last update 03/04
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }
}