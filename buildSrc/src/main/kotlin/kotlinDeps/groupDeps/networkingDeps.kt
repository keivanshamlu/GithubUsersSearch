package kotlinDeps.groupDeps

import kotlinDeps.Network
import org.gradle.api.artifacts.dsl.DependencyHandler
import utility.implementation
import utility.testImplementation

fun DependencyHandler.networking() {
    Network.OkHttp.run {
        implementation(core)
        implementation(logger)
        testImplementation(test)
    }
    Network.Retrofit.run {
        implementation (core)
        implementation (gsonConverter)
    }
}