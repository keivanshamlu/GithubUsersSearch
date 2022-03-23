package com.shamlou.core.modules

import android.app.Application
import android.content.Context
import com.shamlou.core.anots.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val application: Application) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application.applicationContext

    @Provides
    fun provideApplication(): Application = application
}
