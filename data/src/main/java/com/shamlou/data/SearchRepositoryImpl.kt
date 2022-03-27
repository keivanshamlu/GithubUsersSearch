package com.shamlou.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.dataSource.DEFAULT_PAGE_SIZE
import com.shamlou.data.dataSource.SearchPagingSource
import com.shamlou.data.model.search.ResponseItemsRemote
import com.shamlou.data.services.SearchApi
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.repo.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * centralize remote and local data
 * bases and provides data for presentation
 */
class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchApi,
    private val mapperSearchResultRemoteToDomain: Mapper<ResponseItemsRemote, ResponseItemsDomain>,
) : SearchRepository {

    var dataSource : SearchPagingSource? = null

    @ExperimentalPagingApi
    override fun search(param: String): Flow<PagingData<ResponseItemsDomain>> {

        dataSource?.invalidate()
        dataSource = SearchPagingSource(
            searchService,
            param,
            mapperSearchResultRemoteToDomain
        )

        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE
            ),
            pagingSourceFactory = {
                dataSource!!
            }
        ).flow
    }
}