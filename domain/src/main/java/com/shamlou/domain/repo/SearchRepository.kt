package com.shamlou.domain.repo

import androidx.paging.PagingData
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun search(param: String): Flow<PagingData<ResponseItemsDomain>>
    fun userDetails(param: String): Flow<ResponseUserDetailDomain>
}