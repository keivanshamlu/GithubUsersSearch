package com.shamlou

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import com.google.android.material.snackbar.Snackbar
import com.shamlou.bases_android.useCase.UseCaseBaseFlow
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.navigation.command.ActionCommand
import com.shamlou.navigation.command.NavigationCommand
import com.shamlou.navigation.command.NavigationFlow
import com.shamlou.search.*
import com.shamlou.search.ResponseSearchFakers.validResponseItemsDomain
import com.shamlou.search.ui.search.LAST_QUERY
import com.shamlou.search.ui.search.SearchViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: SearchViewModel

    //used when we want to make sure set method is called
    @MockK(relaxed = true)
    lateinit var savedStateHandleMock: SavedStateHandle

    private var getSearchUseCase: UseCaseBaseFlow<String, PagingData<ResponseItemsDomain>> =
        UseCaseGetSearchResultPagedSuccess()


    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = SearchViewModel(
            savedStateHandleMock,
            getSearchUseCase
        )
        stubDefault()
    }

    private fun stubDefault() {

        // mockk way to stub Extension functions
        // (stub map and mapListed function of resource)
        mockkStatic(RESOURCES_PACKAGE)//mockk way to stub Extension functions
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun searchUser_shouldBeCalledSuccess_whenUseCaseGetSuccessful() =
        mainCoroutineRule.dispatcher.runBlockingTest{

            //given
            val differ = AsyncPagingDataDiffer(
                diffCallback = MyDiffCallback(),
                updateCallback = NoopListCallback(),
                workerDispatcher = Dispatchers.Main
            )

            //when
            val response = viewModel.searchUser(ResponseSearchFakers.exampleUserName)

            //then
            val emitted = getLastEmitted(response!!)
            differ.submitData(emitted!!)
            assertEquals(
                ResponseSearchFakers.itemsDomain,
                differ.snapshot().items
            )
            //item should be saved in savedStateHandle
            verify { savedStateHandleMock.set(LAST_QUERY, ResponseSearchFakers.exampleUserName) }
        }
    @Test
    @ExperimentalCoroutinesApi
    fun searchUser_shouldCallUseCaseOnlyOnce_inConfigureChanges() =
        mainCoroutineRule.dispatcher.runBlockingTest{

            //when
            //call twice (configure changes)
            viewModel.searchUser(ResponseSearchFakers.exampleUserName)
            every { savedStateHandleMock.get<String>(LAST_QUERY) } returns ResponseSearchFakers.exampleUserName
            viewModel.searchUser(ResponseSearchFakers.exampleUserName)

            //then
            //should be called exactly one time
            verify(exactly = 1) { savedStateHandleMock.set(LAST_QUERY, ResponseSearchFakers.exampleUserName) }
        }

    @Test
    @ExperimentalCoroutinesApi
    fun navigateToUserDetails_shouldNavigateUserToUserDetail() =
        mainCoroutineRule.dispatcher.runBlockingTest {

            //when
            viewModel.navigateToUserDetails(validResponseItemsDomain)

            //then
            val emitted = getLastEmitted(viewModel.navigationCommand)
            assertEquals(
                NavigationCommand.To(NavigationFlow.ToUserDetails("login")),
                emitted?.getContentIfNotHandled()
            )
        }


    @Test
    @ExperimentalCoroutinesApi
    fun handlePagingError_shouldShowErrorMessageAsSnackbar() =
        mainCoroutineRule.dispatcher.runBlockingTest {

            //when
            viewModel.handlePagingError(ResponseSearchFakers.sampleError)

            //then
            val emitted = getLastEmitted(viewModel.actionCommand)
            assertEquals(
                ActionCommand.ShowSnackBar(ResponseSearchFakers.sampleError.message ?: "", Snackbar.LENGTH_LONG),
                emitted?.getContentIfNotHandled()
            )
        }
}