package com.shamlou.sample.di.features.search

import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.mappers.MapperResponseSearchRemoteToDomain
import com.shamlou.data.mappers.MapperResponseUserDetailsRemoteToDomain
import com.shamlou.data.model.search.ResponseItemsRemote
import com.shamlou.data.model.userDetail.ResponseUserDetailRemote
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
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
    abstract fun bindMapperResponseSearchRemoteToDomain(mapper: MapperResponseSearchRemoteToDomain): Mapper<ResponseItemsRemote, ResponseItemsDomain>

    @Binds
    @SearchScope
    abstract fun bindMapperResponseUserDetailsRemoteToDomain(mapper: MapperResponseUserDetailsRemoteToDomain): Mapper<ResponseUserDetailRemote, ResponseUserDetailDomain>
}