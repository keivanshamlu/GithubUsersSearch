package testDeps

object Test {
    private const val coreVersion = "1.4.0-alpha06"
    private const val robolectricVersion = "4.7.3"
    private const val junitVersion = "4.12"
    private const val jsonVersion = "20180813"
    private const val runnerVersion = "1.2.0"
    private const val fakerVersion = "1.0.2"

    const val barista = "com.schibsted.spain:barista:3.6.0"
    const val json = "org.json:json:$jsonVersion"
    const val androidCore = "androidx.test:core:$coreVersion"
    const val robolectric = "org.robolectric:robolectric:$robolectricVersion"

    const val core = "androidx.test:core-ktx:$runnerVersion"
    const val junitKtx = "androidx.test.ext:junit-ktx:1.1.1"
    const val junit = "junit:junit:4.+"
    const val runner = "androidx.test:runner:$runnerVersion"
    const val rules = "androidx.test:rules:$runnerVersion"
    const val truth = "androidx.test.ext:truth:$runnerVersion"
    const val faker = "com.github.javafaker:javafaker:$fakerVersion"

    object Espresso {
        private const val version = "3.4.0-alpha06"
        const val core = "androidx.test.espresso:espresso-core:$version"// last update 02/16
        const val contrib = "androidx.test.espresso:espresso-contrib:$version"
        const val intents = "androidx.test.espresso:espresso-intents:$version"
        const val accessibility = "androidx.test.espresso:espresso-accessibility:$version"
        const val web = "androidx.test.espresso:espresso-web:$version"
        const val concurrent = "androidx.test.espresso.idling:idling-concurrent:$version"
    }

    object Mockk {
        private const val version = "1.12.1"
        const val mockk = "io.mockk:mockk:$version"
        const val androidMockk = "io.mockk:mockk-android:$version"
    }

    object Mockito {
        private const val mockitoKotlinVersion = "2.2.0"
        private const val mockitoCoreVersion = "3.7.0"
        const val mockito = "org.mockito:mockito-core:$mockitoCoreVersion"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion"
    }
}