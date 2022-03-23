package com.shamlou.sample.di

import com.shamlou.core.components.CoreComponent
import com.shamlou.sample.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class AppScope

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent : AndroidInjector<Application> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }

}
