package androidDeps.groupDeps

import androidDeps.AndroidX.Glide
import org.gradle.api.artifacts.dsl.DependencyHandler
import utility.implementation
import utility.kapt

fun DependencyHandler.glide() {
    Glide.run {
        implementation(glide)
        kapt(compiler)
    }
}


