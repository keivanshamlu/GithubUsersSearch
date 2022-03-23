package com.shamlou.search.di

import androidx.lifecycle.ViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.core.anots.ViewModelKey
import com.shamlou.search.ui.search.SearchViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_SearchAssistedModule::class])
abstract class SearchAssistedModule {

    @Binds
    @IntoMap
    @SearchScope
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindVMFactory(f: SearchViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}