package com.shamlou.data.mappers


import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.model.search.ResponseItemsRemote
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.ResponseSearchFakers.validResponseItemsDomain
import com.shamlou.data.utility.ResponseSearchFakers.validResponseItemsDomain2
import com.shamlou.data.utility.ResponseSearchFakers.validResponseItemsDomain3
import com.shamlou.data.utility.ResponseSearchFakers.validResponseItemsRemote
import com.shamlou.data.utility.ResponseSearchFakers.validResponseItemsRemote2
import com.shamlou.data.utility.ResponseSearchFakers.validResponseItemsRemote3
import com.shamlou.domain.model.search.ResponseItemsDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MapperResponseSearchRemoteToDomainTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    var mapper : Mapper<ResponseItemsRemote, ResponseItemsDomain> = MapperResponseSearchRemoteToDomain()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidInput1() = mainCoroutineRule.runBlockingTest {

        //when
        val response = mapper.map(validResponseItemsRemote)
        //then
        Assert.assertEquals(response , validResponseItemsDomain)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidInput2() = mainCoroutineRule.runBlockingTest {

        //when
        val response = mapper.map(validResponseItemsRemote2)
        //then
        Assert.assertEquals(response , validResponseItemsDomain2)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidInput3() = mainCoroutineRule.runBlockingTest {

        //when
        val response = mapper.map(validResponseItemsRemote3)
        //then
        Assert.assertEquals(response , validResponseItemsDomain3)
    }
}