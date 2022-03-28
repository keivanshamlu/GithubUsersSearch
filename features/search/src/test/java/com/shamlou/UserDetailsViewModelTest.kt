package com.shamlou

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.android.material.snackbar.Snackbar
import com.shamlou.bases_android.useCase.Resource
import com.shamlou.bases_android.useCase.UseCaseBaseFlowResource
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import com.shamlou.navigation.command.ActionCommand
import com.shamlou.search.MainCoroutineRule
import com.shamlou.search.ResponseSearchFakers.exampleUserName
import com.shamlou.search.ResponseSearchFakers.sampleError
import com.shamlou.search.ResponseSearchFakers.validResponseUserDetailDomain
import com.shamlou.search.getLastEmitted
import com.shamlou.search.getListEmitted
import com.shamlou.search.ui.userDetails.USER_NAME
import com.shamlou.search.ui.userDetails.UserDetailsViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

const val RESOURCES_PACKAGE = "com.shamlou.bases_android.useCase.ResourceKt"

@ExperimentalCoroutinesApi
class UserDetailsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: UserDetailsViewModel

    // used when we want to set data
    // and see the view model reaction
    val savedStateHandle = SavedStateHandle()

    //used when we want to make sure set method is called
    @MockK(relaxed = true)
    lateinit var savedStateHandleMock: SavedStateHandle

    private var getUserDetailUseCase: UseCaseBaseFlowResource<String, ResponseUserDetailDomain> =
        UseCaseGetUserDetailUseCaseSuccess()

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = UserDetailsViewModel(
            savedStateHandle,
            getUserDetailUseCase
        )
        stubDefault()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun stubDefault() {

        // mockk way to stub Extension functions
        // (stub map and mapListed function of resource)
        mockkStatic(RESOURCES_PACKAGE)//mockk way to stub Extension functions
    }

    @Test
    fun setUserName_shouldSaveInstance() =
        mainCoroutineRule.dispatcher.runBlockingTest {
            //when
            viewModel = UserDetailsViewModel(
                savedStateHandleMock,
                getUserDetailUseCase
            )
            //when
            viewModel.setUserName(exampleUserName)
            verify { savedStateHandleMock.set(USER_NAME, exampleUserName) }
        }

    @Test
    @ExperimentalCoroutinesApi
    fun getUserDetails_shouldBeCalledSuccess_whenTextChangesAndUseCaseReturnSuccessful() =
        mainCoroutineRule.dispatcher.runBlockingTest {

            //when
            viewModel.setUserName(exampleUserName)

            //then
            val emitted = getLastEmitted(viewModel.userDetails)
            Assert.assertEquals(emitted.status, Resource.Status.SUCCESS)
            Assert.assertEquals(emitted.data, validResponseUserDetailDomain)
        }
    @Test
    @ExperimentalCoroutinesApi
    fun getUserDetails_shouldBeCalledOnlyOneTime_whenTextChangesTwiceInConfigureChangesAndUseCaseGetSuccessful() =
        mainCoroutineRule.dispatcher.runBlockingTest {

            //when
            viewModel.setUserName(exampleUserName)
            viewModel.setUserName(exampleUserName)

            //then
            //valid item emitted
            val emitted = getLastEmitted(viewModel.userDetails)
            Assert.assertEquals(emitted.status, Resource.Status.SUCCESS)
            Assert.assertEquals(emitted.data, validResponseUserDetailDomain)
        }

    @Test
    @ExperimentalCoroutinesApi
    fun getUserDetails_shouldShowSnackBarError_whenUseCaseGetFailure() =
        mainCoroutineRule.dispatcher.runBlockingTest {

            // given
            // I used UseCaseGetUserDetailUseCaseFailure which is a failure faker
            viewModel = UserDetailsViewModel(
                savedStateHandle,
                UseCaseGetUserDetailUseCaseFailure()
            )
            //when
            viewModel.setUserName(exampleUserName)

            //then
            //error emitted
            val emitted = getLastEmitted(viewModel.userDetails)
            Assert.assertEquals(emitted.status, Resource.Status.ERROR)

            //snack bar shown
            val emittedAction = getLastEmitted(viewModel.actionCommand)
            Assert.assertEquals(
                emittedAction?.getContentIfNotHandled(),
                ActionCommand.ShowSnackBar(sampleError.message ?: "", Snackbar.LENGTH_LONG)
            )
            //only once
            val emittedActionList = getListEmitted(viewModel.actionCommand)
            Assert.assertEquals(
                emittedActionList.size,
                1
            )
        }
}

