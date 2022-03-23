package androidDeps.groupDeps

import androidDeps.AndroidX
import org.gradle.api.artifacts.dsl.DependencyHandler
import utility.implementation
import utility.testImplementation
import utility.kapt

fun DependencyHandler.naviagtion() {
    AndroidX.Navigation.run {
        implementation (fragment)
        implementation (uiKtx)
        implementation (ui)
        implementation (core)
    }
}
fun DependencyHandler.room() {
    AndroidX.Room.run {
        kapt (compiler)
        implementation (core)
        implementation (runtime)
    }
}
fun DependencyHandler.arch() {
    AndroidX.Arch.run {
        implementation (core)
        implementation (runtime)
        testImplementation(test)
    }
}
fun DependencyHandler.lifeCycle() {
    AndroidX.LifeCycle.run {
        implementation (liveData)
        implementation (process)
        implementation (runtime)
        implementation (commonJava8)
        implementation (viewModel)
    }
}
fun DependencyHandler.fragment() {
    AndroidX.Fragment.run {
        implementation (fragment)
        implementation (fragmentKtx)
    }
}
fun DependencyHandler.androidDefault() {
    AndroidX.run {
        implementation (extensionsCore)
        implementation (appCompat)
        implementation (material)
        implementation (constraintLayout)
        implementation (maps)
    }
}
fun DependencyHandler.databinding() {
    implementation (AndroidX.DataBinding.dataBinding)
}
