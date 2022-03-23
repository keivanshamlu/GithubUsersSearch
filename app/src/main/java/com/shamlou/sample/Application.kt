package com.shamlou.sample

import com.shamlou.core.modules.ContextModule
import com.shamlou.core.components.CoreComponent
import com.shamlou.core.components.DaggerCoreComponent
import com.shamlou.sample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class Application : DaggerApplication() {

    private lateinit var coreComponent: CoreComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .coreComponent(getCoreComponent())
            .build()
    }

    private fun getCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .contextModule(ContextModule(this))
                .build()
        }
        return coreComponent
    }

}