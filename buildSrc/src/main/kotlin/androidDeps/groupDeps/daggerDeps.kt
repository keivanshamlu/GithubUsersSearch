package androidDeps.groupDeps

import androidDeps.DependencyInjection
import org.gradle.api.artifacts.dsl.DependencyHandler
import utility.compileOnly
import utility.implementation
import utility.kapt

fun DependencyHandler.daggerKotlin() {
    DependencyInjection.Dagger.run {
        implementation(runtime)
    }
}
fun DependencyHandler.daggerAndroid() {
    DependencyInjection.Dagger.run {
        daggerKotlin()
        implementation(android)
        implementation(android_support)
        kapt(compiler)
        kapt(android_support_compiler)
    }
}
fun DependencyHandler.daggerAssisted() {
    DependencyInjection.Dagger.Assisted.run {
        compileOnly(annotations)
        kapt(processor)
    }
}