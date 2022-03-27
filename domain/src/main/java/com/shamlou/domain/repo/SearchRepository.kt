package com.shamlou.domain.repo

import androidx.paging.PagingData
import com.shamlou.domain.model.search.ResponseItemsDomain
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun search(param: String): Flow<PagingData<ResponseItemsDomain>>
}