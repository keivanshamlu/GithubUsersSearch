package com.shamlou.sample.di

import com.shamlou.sample.di.features.search.SearchMappersModule
import com.shamlou.sample.di.features.search.SearchModule
import com.shamlou.search.di.SearchAssistedModule
import com.shamlou.search.di.SearchScope
import com.shamlou.search.ui.search.FragmentSearch
import com.shamlou.search.ui.userDetails.FragmentUserDetails
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBindingModule {

    @SearchScope
    @ContributesAndroidInjector(
        modules = [
            SearchAssistedModule::class,
            SearchModule::class,
            SearchMappersModule::class]
    )
    internal abstract fun bindSearch(): FragmentSearch

    @SearchScope
    @ContributesAndroidInjector(
        modules = [
            SearchAssistedModule::class,
            SearchModule::class,
            SearchMappersModule::class]
    )
    internal abstract fun bindUserDetails(): FragmentUserDetails

}