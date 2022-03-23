package testDeps.groupDeps

import org.gradle.api.artifacts.dsl.DependencyHandler
import testDeps.Test
import utility.testImplementation
import utility.androidTestImplementation
import androidDeps.AndroidX
import kotlinDeps.Kotlin
import kotlinDeps.Network

fun DependencyHandler.test() {
    Test.run {
        testImplementation(junit)
        testImplementation(core)
        testImplementation(json)
        testImplementation(androidCore)
        testImplementation(robolectric)
        testImplementation(junitKtx)
        testImplementation(runner)
        testImplementation(rules)
        testImplementation(truth)
        testImplementation(faker)
    }
    Test.Mockk.run {
        testImplementation(mockk)
    }
}

fun DependencyHandler.testAndroid() {

    androidTestImplementation(Test.junit)
    androidTestImplementation(Test.core)
    androidTestImplementation(Test.json)
    androidTestImplementation(Test.androidCore)
    androidTestImplementation(Test.robolectric)
    androidTestImplementation(Test.junitKtx)
    androidTestImplementation(Test.runner)
    androidTestImplementation(Test.rules)
    androidTestImplementation(Test.truth)
    androidTestImplementation(Test.faker)
    androidTestImplementation(Network.OkHttp.test)
    androidTestImplementation(Test.Mockk.androidMockk)
    androidTestImplementation(AndroidX.Arch.test)
    androidTestImplementation(AndroidX.Fragment.test)
    androidTestImplementation(AndroidX.Navigation.test)
    androidTestImplementation(Kotlin.Coroutine.test)
}
