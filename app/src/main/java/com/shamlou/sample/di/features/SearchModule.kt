package com.shamlou.sample.di.features

import com.shamlou.bases_android.useCase.UseCaseBaseLiveData
import com.shamlou.core.assisted.InjectingSavedStateViewModelFactory
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.data.SearchRepositoryImpl
import com.shamlou.data.services.SearchApi
import com.shamlou.domain.model.search.ResponseSearchDomain
import com.shamlou.domain.repo.SearchRepository
import com.shamlou.domain.usecases.search.SearchUseCase
import com.shamlou.search.di.SearchScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.*

@Module
abstract class SearchModule {


    @Binds
    @SearchScope
    abstract fun bindSearchRepository(repo : SearchRepositoryImpl) : SearchRepository

    @Binds
    @SearchScope
    abstract fun bindSearchUseCase(useCase : SearchUseCase): UseCaseBaseLiveData<String, ResponseSearchDomain>

    @Binds
    @SearchScope
    abstract fun bindViewModelFactory(mapper: InjectingSavedStateViewModelFactory): ViewModelFactory

    companion object {

        @Provides
        @SearchScope
        fun provideSearchService(retrofit : Retrofit): SearchApi {
            return retrofit.create(SearchApi::class.java)
        }
    }
}