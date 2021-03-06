
import androidDeps.groupDeps.lifeCycle
import androidDeps.groupDeps.room
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
        id(GOOGLE_SECRETS_PLUGIN)
    }
}
androidLib()
dependencies {
    Modules.run {

        implementation(project(DOMAIN))
        implementation(project(BASES))
    }
    networking()
    room()
    lifeCycle()
}