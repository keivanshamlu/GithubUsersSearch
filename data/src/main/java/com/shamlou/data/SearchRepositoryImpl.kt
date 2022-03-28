package com.shamlou.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.dataSource.DEFAULT_PAGE_SIZE
import com.shamlou.data.dataSource.SearchPagingSource
import com.shamlou.data.model.search.ResponseItemsRemote
import com.shamlou.data.model.userDetail.ResponseUserDetailRemote
import com.shamlou.data.services.SearchApi
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import com.shamlou.domain.repo.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * centralize remote and local data
 * bases and provides data for presentation
 */
class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchApi,
    private val mapperSearchResultRemoteToDomain: Mapper<ResponseItemsRemote, ResponseItemsDomain>,
    private val mapperResponseUserDetailsRemoteToDomain: Mapper<ResponseUserDetailRemote, ResponseUserDetailDomain>,
) : SearchRepository {

    // invalidates dataSource and then create new
    // dataSource with new query, and the returns
    // pager as flow to be passed to presentation layer
    @ExperimentalPagingApi
    override fun search(param: String): Flow<PagingData<ResponseItemsDomain>> = Pager(
        config = PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE
        ),
        pagingSourceFactory = {
            SearchPagingSource(
                searchService,
                param,
                mapperSearchResultRemoteToDomain
            )
        }
    ).flow

    override fun userDetails(param: String): Flow<ResponseUserDetailDomain> = flow {
        Log.d("TESTEST", param)
        val response = searchService.getUserDetails(param)
        emit(mapperResponseUserDetailsRemoteToDomain.map(response))
    }
}