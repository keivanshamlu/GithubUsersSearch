package com.shamlou.data.dataSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.model.search.ResponseItemsRemote
import com.shamlou.data.services.SearchApi
import com.shamlou.domain.model.search.ResponseItemsDomain
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val DEFAULT_PAGE_SIZE = 10
const val DEFAULT_PAGE_INDEX = 1

class SearchPagingSource @Inject  constructor(
    private val service: SearchApi,
    private val query: String,
    private val mapperSearchResultRemoteToDomain: Mapper<ResponseItemsRemote, ResponseItemsDomain>,
) :
    PagingSource<Int, ResponseItemsDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseItemsDomain> {
        // last key or first page
        val pageIndex = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            // getting response from remote
            // since pager works with page number,
            // we calculate offset with page size
                Log.d("TESTEST", pageIndex.toString())
            if(query.isEmpty()){
                return LoadResult.Page(
                    data = listOf(),
                    prevKey = if (pageIndex == DEFAULT_PAGE_INDEX) null else pageIndex,
                    nextKey = null
                )
            }
            val response = service.searchGithubUser(
                page = pageIndex,
                perPage = DEFAULT_PAGE_SIZE,
                query = query
            )
            val searchResult = response.items

            //calculate next key to be loaded
            val nextKey =
                if (searchResult.isNullOrEmpty()) {
                    null
                } else {
                    pageIndex + 1
                }

            LoadResult.Page(
                data = searchResult.map { mapperSearchResultRemoteToDomain.map(it) },
                prevKey = if (pageIndex == DEFAULT_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, ResponseItemsDomain>): Int {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        Log.d("TESTEST", "refresh")
        return DEFAULT_PAGE_INDEX
    }
}
