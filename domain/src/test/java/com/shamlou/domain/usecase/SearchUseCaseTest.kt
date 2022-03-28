package com.shamlou.domain.usecase

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.MyDiffCallback
import com.shamlou.data.utility.NoopListCallback
import com.shamlou.data.utility.ResponseSearchFakers.exampleUserName
import com.shamlou.data.utility.ResponseSearchFakers.itemsDomain
import com.shamlou.domain.repo.SearchRepository
import com.shamlou.domain.usecases.search.SearchUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SearchUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    lateinit var useCase : SearchUseCase

    @MockK
    lateinit var repository: SearchRepository

    @Before
    fun setUp(){

        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        useCase = SearchUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun execute_shouldCallGetPagedSearchResult_whenSuccessful() {

        runBlocking {

            coEvery { repository.search(exampleUserName) } returns flow {
                emit(
                    PagingData.from(
                        itemsDomain
                    )
                )
            }

            val differ = AsyncPagingDataDiffer(
                diffCallback = MyDiffCallback(),
                updateCallback = NoopListCallback(),
                workerDispatcher = Dispatchers.Main
            )

            val a = useCase.invoke(exampleUserName).first()
            differ.submitData(a)

            assertEquals(itemsDomain, differ.snapshot().items)
        }
    }
}