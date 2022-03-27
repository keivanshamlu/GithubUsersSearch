package androidDeps

object AndroidX {

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"// last update 02/04
    const val extensionsCore = "androidx.core:core-ktx:1.6.0-rc01"// last update 02/04
    const val appCompat = "androidx.appcompat:appcompat:1.4.1"
    const val material = "com.google.android.material:material:1.4.0"
    const val maps = "com.google.android.gms:play-services-maps:17.0.0"

    object Fragment {
        private const val fragment_version = "1.3.3"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragment_version"
        const val fragment = "androidx.fragment:fragment:$fragment_version"
        const val test = "androidx.fragment:fragment-testing:$fragment_version"
    }

    object LifeCycle {
        private const val lifecycle_version = "2.4.0"
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
        const val process = "androidx.lifecycle:lifecycle-process:$lifecycle_version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"

    }

    object DataBinding {

        private const val version = "3.5.0"
        const val dataBinding = "androidx.databinding:databinding-compiler:$version"
    }

    object Arch {
        private const val version = "2.1.0"
        const val core = "androidx.arch.core:core-common:$version"
        const val runtime = "androidx.arch.core:core-runtime:$version"
        const val test = "androidx.arch.core:core-testing:$version"
    }

    object Pagination {

        private const val paging_version = "3.1.0"
        const val pagination = "androidx.paging:paging-runtime:${paging_version}"
        const val paginationCommon = "androidx.paging:paging-common:${paging_version}"

    }
    object RecyclerView {

        private const val recycler_version = "1.2.0"
        const val recycler = "androidx.recyclerview:recyclerview:${recycler_version}"
    }

    object Navigation {
        const val version = "2.3.5"
        const val core = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui:$version"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        const val fragment = "androidx.navigation:navigation-fragment:$version"
        const val test = "androidx.navigation:navigation-testing:$version"
    }

    object Room {
        private const val version = "2.2.6"
        const val compiler = "androidx.room:room-compiler:$version"
        const val core = "androidx.room:room-ktx:$version"
        const val runtime = "androidx.room:room-runtime:$version"
    }

    object Glide {
        private const val version = "4.12.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }
}