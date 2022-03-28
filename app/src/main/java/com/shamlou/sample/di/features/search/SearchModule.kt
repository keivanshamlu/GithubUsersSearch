package com.shamlou.sample.di.features.search

import androidx.paging.PagingData
import com.shamlou.bases_android.useCase.UseCaseBaseFlow
import com.shamlou.bases_android.useCase.UseCaseBaseFlowResource
import com.shamlou.core.assisted.InjectingSavedStateViewModelFactory
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.data.SearchRepositoryImpl
import com.shamlou.data.services.SearchApi
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import com.shamlou.domain.repo.SearchRepository
import com.shamlou.domain.usecases.search.SearchUseCase
import com.shamlou.domain.usecases.userDetails.UserDetailUseCase
import com.shamlou.search.di.SearchScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class SearchModule {


    @Binds
    @SearchScope
    abstract fun bindSearchRepository(repo : SearchRepositoryImpl) : SearchRepository

    @Binds
    @SearchScope
    abstract fun bindSearchUseCase(useCase : SearchUseCase): UseCaseBaseFlow<String, PagingData<ResponseItemsDomain>>

    @Binds
    @SearchScope
    abstract fun bindUserDetailUseCase(useCase : UserDetailUseCase): UseCaseBaseFlowResource<String, ResponseUserDetailDomain>

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