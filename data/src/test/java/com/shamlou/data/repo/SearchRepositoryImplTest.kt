package com.shamlou.data.repo

import androidx.paging.ExperimentalPagingApi
import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.SearchRepositoryImpl
import com.shamlou.data.model.search.ResponseItemsRemote
import com.shamlou.data.model.userDetail.ResponseUserDetailRemote
import com.shamlou.data.services.SearchApi
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.ResponseSearchFakers.exampleUserName
import com.shamlou.data.utility.ResponseSearchFakers.sampleError
import com.shamlou.data.utility.ResponseSearchFakers.validResponseItemsDomain
import com.shamlou.data.utility.ResponseSearchFakers.validResponseItemsRemote
import com.shamlou.data.utility.ResponseSearchFakers.validResponseUserDetailDomain
import com.shamlou.data.utility.ResponseSearchFakers.validResponseUserDetailRemote
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class SearchRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var repo: SearchRepositoryImpl

    @MockK(relaxed = true)
    lateinit var searchService: SearchApi

    @MockK(relaxed = true)
    lateinit var mapperSearchResultRemoteToDomain: Mapper<ResponseItemsRemote, ResponseItemsDomain>

    @MockK(relaxed = true)
    lateinit var mapperResponseUserDetailsRemoteToDomain: Mapper<ResponseUserDetailRemote, ResponseUserDetailDomain>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        repo = SearchRepositoryImpl(
            searchService, mapperSearchResultRemoteToDomain, mapperResponseUserDetailsRemoteToDomain
        )
        setUpDefaultValidStubs()
    }

    private fun setUpDefaultValidStubs(){
        coEvery { mapperSearchResultRemoteToDomain.map(validResponseItemsRemote) } returns validResponseItemsDomain
        coEvery { mapperResponseUserDetailsRemoteToDomain.map(any()) } returns validResponseUserDetailDomain
    }

    @Test
    @ExperimentalCoroutinesApi
    fun userDetails_shouldReturnCompatibleDomainModel_whenRemoteReturnValidDataModel() = mainCoroutineRule.runBlockingTest {

        //given
        setUpDefaultValidStubs()
        coEvery { searchService.getUserDetails(exampleUserName) } returns validResponseUserDetailRemote

        //when
        val response = repo.userDetails(exampleUserName).first()
        //then
        verify { mapperResponseUserDetailsRemoteToDomain.map(validResponseUserDetailRemote) }
        assertEquals(response , validResponseUserDetailDomain)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun userDetails_shouldReturnCompatibleDomainModel_whenRemoteThrowError() = mainCoroutineRule.runBlockingTest {

        //given
        setUpDefaultValidStubs()
        coEvery { searchService.getUserDetails(exampleUserName) } throws sampleError
        try {
            //when
            repo.userDetails(exampleUserName).first()
        }catch (e : Exception){
            //then
            assertEquals(e.javaClass, sampleError.javaClass)
            assertEquals(e.message, sampleError.message)
        }
    }
}