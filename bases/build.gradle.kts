import androidDeps.groupDeps.glide
import configs.androidFeature
import modules.Modules

plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        kotlin(ANDROID)
        kotlin(KAPT)
        id(NAVIGATION_SAFEARGS_KOTLIN)
        id(ANDROID_EXTENSIONS)
    }
}
androidFeature()
dependencies {
    implementation(project(Modules.NAVIGATION))
    glide()
}