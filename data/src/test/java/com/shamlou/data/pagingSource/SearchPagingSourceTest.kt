package com.shamlou.data.pagingSource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.dataSource.SearchPagingSource
import com.shamlou.data.model.search.ResponseItemsRemote
import com.shamlou.data.services.SearchApi
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.ResponseSearchFakers
import com.shamlou.data.utility.ResponseSearchFakers.exampleUserName
import com.shamlou.data.utility.ResponseSearchFakers.itemsDomain
import com.shamlou.data.utility.ResponseSearchFakers.responseRemote
import com.shamlou.data.utility.ResponseSearchFakers.sampleError
import com.shamlou.domain.model.search.ResponseItemsDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class SearchPagingSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var searchPagingSource: SearchPagingSource

    @MockK(relaxed = true)
    lateinit var service: SearchApi
    @MockK
    lateinit var mapperSearchResultRemoteToDomain: Mapper<ResponseItemsRemote, ResponseItemsDomain>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        searchPagingSource = SearchPagingSource(service , ResponseSearchFakers.exampleUserName, mapperSearchResultRemoteToDomain)
    }


    private fun setUpPagedListValidStubs(){

        coEvery { mapperSearchResultRemoteToDomain.map(ResponseSearchFakers.validResponseItemsRemote) } returns ResponseSearchFakers.validResponseItemsDomain
        coEvery { mapperSearchResultRemoteToDomain.map(ResponseSearchFakers.validResponseItemsRemote2) } returns ResponseSearchFakers.validResponseItemsDomain2
        coEvery { mapperSearchResultRemoteToDomain.map(ResponseSearchFakers.validResponseItemsRemote3) } returns ResponseSearchFakers.validResponseItemsDomain3
    }

    @Test
    @ExperimentalCoroutinesApi
    fun searchGithubUser_shouldReturnCompatibleDomainModel_whenRemoteReturnValidDataModel() = runBlockingTest {

        //given
        coEvery { service.searchGithubUser(1, 10, exampleUserName) } returns responseRemote

        setUpPagedListValidStubs()

        //then
        assertEquals(
            PagingSource.LoadResult.Page(
                data = itemsDomain,
                prevKey = null,
                nextKey = 2
            ),
            searchPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }
    @Test
    @ExperimentalCoroutinesApi
    fun searchGithubUser_shouldReturnCompatibleDomainModel_whenRemoteThrowError() = runBlockingTest {

        //given
        coEvery { service.searchGithubUser(1, 10, exampleUserName) } throws sampleError

        //then
        assertEquals(
            PagingSource.LoadResult.Error<Int , ResponseItemsDomain>(sampleError),
            searchPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }
}