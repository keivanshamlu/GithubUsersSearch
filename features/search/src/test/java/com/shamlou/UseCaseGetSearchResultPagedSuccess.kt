package com.shamlou

import androidx.paging.PagingData
import com.shamlou.bases_android.useCase.UseCaseBaseFlow
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.search.ResponseSearchFakers.itemsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// since mockk does not support stubbing for base classes,
// i created this fake classes so i can stub execute()
class UseCaseGetSearchResultPagedSuccess : UseCaseBaseFlow<String, PagingData<ResponseItemsDomain>> {

    override fun execute(parameters: String): Flow<PagingData<ResponseItemsDomain>> {
        return flow { emit(PagingData.from(itemsDomain)) }
    }

    override fun invoke(parameters: String): Flow<PagingData<ResponseItemsDomain>> {
        return flow { emit(PagingData.from(itemsDomain)) }
    }
}
