import androidDeps.groupDeps.room
import configs.androidFeature
import configs.androidLib
import kotlinDeps.groupDeps.networking
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
androidLib()
dependencies {
    Modules.run {

        implementation(project(BASES))
    }
}