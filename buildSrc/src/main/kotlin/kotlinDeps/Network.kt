package kotlinDeps

object Network {
        object OkHttp {
            private const val version = "5.0.0-alpha.2"// last update 02/04
            const val core = "com.squareup.okhttp3:okhttp:$version"
            const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
            const val test = "com.squareup.okhttp3:mockwebserver:$version"
        }

        object Retrofit {
            private const val version = "2.9.0"
            const val core = "com.squareup.retrofit2:retrofit:$version"
            const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
        }
    }