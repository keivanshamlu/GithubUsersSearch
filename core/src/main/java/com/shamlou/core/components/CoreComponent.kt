package com.shamlou.core.components

import android.app.Application
import android.content.Context
import com.shamlou.core.anots.ApplicationContext
import com.shamlou.core.modules.ContextModule
import com.shamlou.core.modules.NetworkModule
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        ContextModule::class]
)
@Singleton
interface CoreComponent {

    @ApplicationContext
    fun provideContext(): Context
    fun application(): Application
    fun getOkhttpClient() : OkHttpClient
    fun getRetrofit(): Retrofit
}