package com.example.myapplication.coroutinetest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CoroutineTestViewModelTestWithRule {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    @Test
    fun testLikeCount1() = runBlocking {
        val coroutineTestViewModel = CoroutineTestViewModel(mainCoroutineRule.testDispatcher)
        val likeCount = coroutineTestViewModel.getLikeCountFromDb()
        Assert.assertEquals(0, likeCount)
    }

    @Test
    fun testAddLikeCount() = mainCoroutineRule.runBlockingTest {
        val coroutineTestViewModel = CoroutineTestViewModel(mainCoroutineRule.testDispatcher)
        coroutineTestViewModel.addLikeCount()
        Assert.assertEquals(1, coroutineTestViewModel.getLikeCountFromDb())
    }
}
