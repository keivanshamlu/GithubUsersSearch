package groupDepsModuleLevel

import androidDeps.groupDeps.*
import kotlinDeps.groupDeps.kotlinCoroutines
import org.gradle.api.artifacts.dsl.DependencyHandler
import testDeps.groupDeps.test
import testDeps.groupDeps.testAndroid

fun DependencyHandler.baseDependencies() {
    kotlinCoroutines()
    daggerKotlin()
    test()
    arch()
}
fun DependencyHandler.baseAndroidDependencies() {
    baseDependencies()
    daggerAndroid()
    testAndroid()
}
fun DependencyHandler.featureModuleBaseDependencies() {
    androidDefault()
    fragment()
    lifeCycle()
    arch()
    naviagtion()
    databinding()
    daggerAssisted()
}