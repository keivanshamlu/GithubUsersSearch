package com.shamlou.domain.usecase

import com.shamlou.bases_android.useCase.Resource
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.ResponseSearchFakers.exampleUserName
import com.shamlou.data.utility.ResponseSearchFakers.sampleError
import com.shamlou.data.utility.ResponseSearchFakers.validResponseUserDetailDomain
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import com.shamlou.domain.repo.SearchRepository
import com.shamlou.domain.usecases.userDetails.UserDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDetailUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var useCase : UserDetailUseCase

    @MockK
    lateinit var repository: SearchRepository

    @Before
    fun setUp(){

        MockKAnnotations.init(this)
        useCase = UserDetailUseCase(repository)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun execute_shouldReturnValidResourceInstance_whenRepoReturnsSuccessfully() {

        runBlocking {
            //given
            coEvery { repository.userDetails(exampleUserName) } returns flow { emit(validResponseUserDetailDomain) }
            //when
            val response = useCase.invoke(exampleUserName).first()
            //then
            coVerify { repository.userDetails(exampleUserName) }
            Assert.assertEquals(response, Resource.success(validResponseUserDetailDomain))
        }
    }
    @Test
    @ExperimentalCoroutinesApi
    fun execute_shouldReturnValidError_whenRepoReturnsError() {

        runBlocking {
            //given
            coEvery { repository.userDetails(exampleUserName) } throws sampleError
            //when
            val response = useCase.invoke(exampleUserName).first()
            //then
            coVerify { repository.userDetails(exampleUserName) }
            Assert.assertEquals(response, Resource.error<ResponseUserDetailDomain>(sampleError))
        }
    }
}