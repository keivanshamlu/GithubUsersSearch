package com.shamlou.data.mappers

import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.model.userDetail.ResponseUserDetailRemote
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.ResponseSearchFakers.validResponseUserDetailDomain
import com.shamlou.data.utility.ResponseSearchFakers.validResponseUserDetailRemote
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MapperResponseUserDetailsRemoteToDomainTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    var mapper : Mapper<ResponseUserDetailRemote, ResponseUserDetailDomain> = MapperResponseUserDetailsRemoteToDomain()

    //thanks to this unit test, i found a bug in mapper and i fixed it
    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidIput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(validResponseUserDetailRemote)
        //then
        Assert.assertEquals(response , validResponseUserDetailDomain)
    }
}