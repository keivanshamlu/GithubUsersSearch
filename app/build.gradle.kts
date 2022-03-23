import androidDeps.AppConfig
import androidDeps.groupDeps.room
import groupDepsModuleLevel.featureModuleBaseDependencies
import kotlinDeps.groupDeps.networking
import modules.Modules
import configs.androidApp

plugins {
    GradlePluginId.apply {
        id(ANDROID_APPLICATION)
        kotlin(ANDROID)
        kotlin(KAPT)
    }
}
androidApp(AppConfig.applicationId)
dependencies {

    Modules.run {
        implementation(project(DOMAIN))
        implementation(project(CORE))
        implementation(project(DESIGN_SYSTEM))
        implementation(project(NAVIGATION))
        implementation(project(BASES))
    }
    Modules.run {
        implementation(project(DATA))
    }
    Modules.Feature.run {
        implementation(project(SEARCH))
    }
    featureModuleBaseDependencies()
    room()
    networking()
}