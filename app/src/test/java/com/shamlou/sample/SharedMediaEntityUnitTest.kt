package com.shamlou.sample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shamlou.data_remote.utility.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SharedMediaEntityUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}