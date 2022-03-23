package androidDeps

object DependencyInjection {

    object Dagger {
        private const val version = "2.40.5"
        const val runtime = "com.google.dagger:dagger:$version"
        const val android = "com.google.dagger:dagger-android:$version"
        const val android_support = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val android_support_compiler =
            "com.google.dagger:dagger-android-processor:$version"

        object Assisted {
            private const val version = "0.8.1"
            const val annotations =
                "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
            const val processor =
                "com.squareup.inject:assisted-inject-processor-dagger2:$version"
        }
    }



}