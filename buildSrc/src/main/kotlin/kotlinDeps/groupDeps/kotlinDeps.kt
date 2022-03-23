package kotlinDeps.groupDeps

import kotlinDeps.Kotlin
import org.gradle.api.artifacts.dsl.DependencyHandler
import utility.implementation
import utility.testImplementation
import utility.androidTestImplementation


fun DependencyHandler.kotlinCoroutines() {
    Kotlin.run {
        implementation (kotlin_stdlib)
        implementation (Kotlin.Coroutine.core)
        implementation (Kotlin.Coroutine.coroutine)
        testImplementation (Kotlin.Coroutine.test)
    }
}
