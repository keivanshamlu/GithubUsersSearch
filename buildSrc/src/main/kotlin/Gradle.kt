import androidDeps.AndroidX
import androidDeps.DependencyInjection
import kotlinDeps.Kotlin

object Gradle {
    const val androidGradleVersion = "4.2.2"
}
object Google {
    const val GOOGLE_SECRETS = "2.0.0"
}
object GradlePluginId {
    const val ANDROID = "android"
    const val ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val JAVA_LIBRARY = "java-library"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN = "kotlin"
    const val KAPT = "kapt"
    const val NAVIGATION_SAFEARGS_KOTLIN = "androidx.navigation.safeargs.kotlin"
    const val KOTLIN_HILT = "dagger.hilt.android.plugin"
}
object GradlePlugins {

    const val ANDROID_GRADLE = "com.android.tools.build:gradle:7.0.4"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${GradlePluginVersion.KOTLIN}"
    const val SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${GradlePluginVersion.SAFE_ARGS}"
    const val GOOGLE_SECRETS = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Google.GOOGLE_SECRETS}"

}
object GradlePluginVersion {

    const val ANDROID_GRADLE = Gradle.androidGradleVersion
    const val KOTLIN = Kotlin.version
    const val SAFE_ARGS = AndroidX.Navigation.version
}