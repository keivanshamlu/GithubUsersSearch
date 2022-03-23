package configs

import androidDeps.AppConfig
import androidDeps.PackagingOptions
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import groupDepsModuleLevel.baseAndroidDependencies
import groupDepsModuleLevel.featureModuleBaseDependencies
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

const val test = "test"
const val androidTest = "androidTest"
const val sharedTestDir = "src/sharedTest/java"
/**
 * for reusing dependencies and build logic in modules
 * i actually wrote a article about it
 * https://medium.com/@keivan.shamlu.ks/how-to-share-dependencies-and-build-config-in-kotlin-dsl-6cdbbc60e39e
 */
val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("$name is not an android module")

fun Project.androidApp(appId: String) {
    androidLib {
        applicationId = appId
    }
}

fun Project.androidLib(
    default: (DefaultConfig.() -> Unit)? = null
) {
    android.run {
        compileSdkVersion(AppConfig.compileSdk)
        defaultConfig {

            AppConfig.run {
                versionCode = vCode
                versionName = vName
                minSdk = minimumSdkVersion
                targetSdk = targettSdkVersion
                testInstrumentationRunner = androidTestInstrumentation
                default?.invoke(this@defaultConfig)
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        dataBinding {
            isEnabled = true
        }
        sourceSets {
            getByName(test).java.srcDirs(sharedTestDir)
            getByName(androidTest).java.srcDirs(sharedTestDir)
        }
        excludePackages()
    }
    dependencies {
        baseAndroidDependencies()
    }
}

/**
 * shared build logic of all features,
 * custom config could be added as default @Param
 */
fun Project.androidFeature(
    default: (DefaultConfig.() -> Unit)? = null
) {

    androidLib(default)
    dependencies {
        featureModuleBaseDependencies()
    }
}

fun Project.excludePackages() {
    android.run {
        PackagingOptions.run {
            packagingOptions.excludes.add(DEPENDENCIES)
            packagingOptions.excludes.add(LICENSE)
            packagingOptions.excludes.add(LICENSE_TEXT)
            packagingOptions.excludes.add(LICENSE_TEXT_2)
            packagingOptions.excludes.add(NOTICE)
            packagingOptions.excludes.add(NOTICE_TEXT)
            packagingOptions.excludes.add(NOTICE_TEXT_2)
            packagingOptions.excludes.add(ASL2)
            packagingOptions.excludes.add(AL2)
            packagingOptions.excludes.add(KOTLIN)
            packagingOptions.excludes.add(LGPL2)
        }
    }
}
