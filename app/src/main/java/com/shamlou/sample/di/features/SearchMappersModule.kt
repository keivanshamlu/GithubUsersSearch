package com.shamlou.sample.di.features

import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.mappers.MapperResponseSearchRemoteToDomain
import com.shamlou.data.model.search.ResponseSearchRemote
import com.shamlou.domain.model.search.ResponseSearchDomain
import com.shamlou.search.di.SearchScope
import dagger.Binds
import dagger.Module

/**
 * bind mappers to their corresponding base type
 */
@Module
abstract class SearchMappersModule {

    @Binds
    @SearchScope
    abstract fun bindMapperResponseSearchRemoteToDomain(mapper: MapperResponseSearchRemoteToDomain): Mapper<ResponseSearchRemote, ResponseSearchDomain>

}